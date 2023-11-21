package com.stackroute.emailservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.emailservice.Services.EmailService;
import com.stackroute.emailservice.Model.EmailRequest;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMail(@RequestBody EmailRequest emailRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            String result = emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
            response.put("status", "success");
            response.put("message", "Mail sent successfully");
            return ResponseEntity.ok(response);
        } catch (MessagingException e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Failed to send email");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/check")
    public String checkEmail(){
        return "Checking email service";
    }
}



