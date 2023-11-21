package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.User;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> getUserByEmail(String email);
    String getUserDetailsByEmail(String email);


}
