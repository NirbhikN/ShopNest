package com.stackroute.userservice.controller;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.ResetToken;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.ResetTokenRepository;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    public UserRepository userRepository;
    private final UserService userService;
    public ResponseEntity responseEntity;

    @Autowired
    private ResetTokenRepository resetTokenRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getUser(){
        return  userRepository.findAll();
    }


    // Register a user
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user){
        return  userService.saveUser(user);
    }

    // Get individual user by Email
    @GetMapping("/email/{email}")
    Optional<User> getUserByEmail(@PathVariable String email){
        return userRepository.getUserByEmail(email);
    }

    @GetMapping("/user/id/{id}")
    Optional<User> getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }




    @GetMapping("/user/{email}")
    public String[] getUserDetailsByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.getUserByEmail(email);
        String var = "id";
        String name = "name";
        String num="num";


        if (user.isPresent()) {
            User user1 = user.get();
            var = user1.getId();
            name = user1.getName();
            num=user1.getContactNum();
        }

        // Create an array to hold var and name
        return new String[]{var, name,num};
    }



    // Update Individual User
    @PutMapping("/edit/{id}")
    public User editUser( @PathVariable String id,@RequestBody User user) throws UserAlreadyExistsException {
        System.out.println(user.getId());
        Optional<User> userOptional= userRepository.findById(id);
        System.out.println(userOptional.get());


            User user1= userOptional.get();
            System.out.println(user1.getId());
            user1.setAddress(user.getAddress());
            System.out.println(user1.getAddress());
            user1.setContactNum(user.getContactNum());
            System.out.println(user1.getContactNum());
            user1.setGender(user.getGender());
            System.out.println(user1.getGender());
            userRepository.save(user1);

        return user1;

    }

    @DeleteMapping("/id/{id}")
    void deleteUser(@PathVariable String id) throws UserAlreadyExistsException {
         userService.delete(id);
    }


    @GetMapping("/check/email/{email}")
    public boolean checkEmail(@PathVariable String email){
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        return optionalUser.isPresent();
    }


//    @PostMapping("/request")
//    public ResponseEntity<String> requestResetPassword(@RequestBody String email) {
//        // Check if the email exists in your user database
//        System.out.println(email);
//        Optional<User> userOptional = userRepository.getUserByEmail(email);
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
//        }
//        User user = userOptional.get();
//        String token = UUID.randomUUID().toString();
//        ResetToken resetToken = new ResetToken(token, user);
//        resetTokenRepository.save(resetToken);
//        return ResponseEntity.ok("Password reset email sent");
//    }



    @PostMapping("/sendOtp")
    public ResponseEntity<?> generateAndSendOTP(@RequestBody String email) {
        Optional<User> userOptional = userRepository.getUserByEmail(email);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        int otp = userService.generateOTP();
        userService.sendOTPEmail(email, otp);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plusMinutes(5);
        ResetToken resetToken = new ResetToken(otp, expiration, user.getEmail());
        resetTokenRepository.save(resetToken);
        return new ResponseEntity<>(otp, HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public boolean verifyOTP(@RequestParam String email, @RequestParam int generatedOTP) {
        return userService.verifyOTP(email, generatedOTP);
    }

    @PostMapping("/reset")
    public boolean  resetPassword(@RequestBody User user){
        System.out.println(user.getEmail());
        return userService.resetPassword(user);
    }

}
