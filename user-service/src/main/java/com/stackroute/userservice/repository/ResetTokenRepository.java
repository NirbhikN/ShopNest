package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.ResetToken;
import com.stackroute.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResetTokenRepository extends MongoRepository<ResetToken, String> {
    ResetToken findByEmail(String email);

}
