package com.stackroute.authenticationservice.config;

import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.rabbit.model.UserRMQ;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.service.UserServiceImp;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Consumer {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private UserRepository userRepository;

    @RabbitListener(queues = "user_queue")
    public void getUserRMQFromRabbitMq(UserRMQ userRMQ) {
        System.out.println(userRMQ.getEmail());
        Optional<User> optionalUser = userRepository.findByEmail(userRMQ.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(userRMQ.getPassword());
            userServiceImp.saveUser(user);
        }
        else{
        User user = new User();
        user.setUserId(userRMQ.getUserId());
        user.setName(userRMQ.getName());
        user.setEmail(userRMQ.getEmail());
        user.setPassword(userRMQ.getPassword());
        userServiceImp.saveUser(user);
    }
}
}
