package com.stackroute.userservice.model;


import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "reset_tokens") // Specify the MongoDB collection name
    public class ResetToken {
        @Id
        private String id = UUID.randomUUID().toString(); // Using String as the ID type for MongoDB
        private int otp;
        private LocalDateTime expirationDateTime;
        private  String email;

    public ResetToken( int otp, LocalDateTime expirationDateTime, String email) {
        this.otp = otp;
        this.expirationDateTime = expirationDateTime;
        this.email = email;
    }

    // Constructors, getters, and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ResetToken() {
    }
}

