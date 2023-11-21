package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(String id) throws UserAlreadyExistsException;

    User saveUser(User user) throws UserAlreadyExistsException;

    User updateUser(String id, User user) throws UserAlreadyExistsException;

    void delete(String id) throws UserAlreadyExistsException;

    String getUserDetailsByEmail(String email);

    int generateOTP();

//    boolean verifyOTP(int enteredOTP, int generatedOTP);

    void sendOTPEmail(String recipientEmail, int otp);

    boolean resetPassword(User user);

    boolean verifyOTP(String userIdentifier, int enteredOTP);
}
