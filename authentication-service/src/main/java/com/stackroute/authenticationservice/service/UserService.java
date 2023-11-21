package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void saveUser(User user);
    public Optional<User> getUserByEmail(String email);
    public List<User> allUser();
}
