package com.stackroute.userservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.config.Producer;
import com.stackroute.userservice.model.ResetToken;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.rabbitmq.model.UserRMQ;
import com.stackroute.userservice.repository.ResetTokenRepository;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;
    @Autowired
    Producer producer;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ResetTokenRepository resetTokenRepository;
    @Override
    public Optional<User> getUserById(String id) throws UserAlreadyExistsException {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        UserRMQ rmq = new UserRMQ();
        rmq.setUserId(user.getId());
        System.out.println(user.getId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        rmq.setName(user.getName());
        rmq.setEmail(user.getEmail());
        rmq.setPassword(hashedPassword);
        user.setPassword(hashedPassword);
        user.setPassword2(hashedPassword);
        Optional<User> optionalUser = userRepository.getUserByEmail(user.getEmail());
        if(optionalUser.isPresent())
        {
            throw new UserAlreadyExistsException("Email already exists");
        }
        producer.sendMessageToRabbitMq(rmq);
//        producer.sendMessageToRabbitMq2(rmq1);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        User newUser=userRepository.findById(id).get();
        newUser.setName(user.getName());
        newUser.setAddress(user.getAddress());
        newUser.setGender(user.getGender());
        newUser.setContactNum(user.getContactNum());
        newUser.setPassword(user.getPassword());
        return userRepository.save(newUser);
    }

    @Override
    public void delete(String id) throws UserAlreadyExistsException {
       userRepository.deleteById(id);

    }

    @Override
    public String getUserDetailsByEmail(String email) {
        return userRepository.getUserDetailsByEmail(email);
    }

    @Override
    public int generateOTP() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

//    @Override
//    public boolean verifyOTP(int enteredOTP, int generatedOTP) {
//        return false;
//    }

    @Override
    public void sendOTPEmail(String recipientEmail, int otp) {
        // Create a SimpleMailMessage
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Your OTP Code");
        mailMessage.setText("Your OTP code is: " + otp);
        javaMailSender.send(mailMessage);
    }



    @Override
    public boolean resetPassword(User user){
        Optional<User> optionalUser = userRepository.getUserByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            UserRMQ rmq = new UserRMQ();
            User user1 = optionalUser.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user1.setPassword(hashedPassword);
            user1.setPassword2(hashedPassword);
            rmq.setEmail(user.getEmail());
            rmq.setPassword(hashedPassword);
            userRepository.save(user1);
            producer.sendMessageToRabbitMq(rmq);
            return true;
        }
        return false;
    }


    @Override
    public boolean verifyOTP(String email, int enteredOTP){
        ResetToken resetToken = resetTokenRepository.findByEmail(email);
        LocalDateTime localDateTime = LocalDateTime.now();
        if(resetToken.getOtp() == enteredOTP && localDateTime.isBefore(resetToken.getExpirationDateTime())){
            resetTokenRepository.delete(resetToken);
            return true;
        }
        return false;
    }

}



//    @Override
//    public boolean verifyOTP(String userIdentifier, int enteredOTP) {
//        Integer storedOTP = otpStorage.get(userIdentifier);
//        if (storedOTP != null && storedOTP == enteredOTP) {
//            otpStorage.remove(userIdentifier); // Remove the OTP after successful verification
//            return true;
//        }
//        return false;
//    }
//    @Override
//    public boolean verifyOTP(String userIdentifier, int enteredOTP) {
//        Integer storedOTP = otpStorage.get(userIdentifier);
//        if (storedOTP != null && storedOTP == enteredOTP) {
//            otpStorage.remove(userIdentifier); // Remove the OTP after successful verification
//            return true;
//        }
//        return false;
//    }

