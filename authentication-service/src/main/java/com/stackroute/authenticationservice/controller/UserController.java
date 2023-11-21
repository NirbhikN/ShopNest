package com.stackroute.authenticationservice.controller;
import com.stackroute.authenticationservice.exception.InvalidCredentialsException;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.security.JwtGeneratorInterface;
import com.stackroute.authenticationservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class UserController {
    private final UserService userService;
    private final JwtGeneratorInterface jwtGenerator;
    private final UserRepository userRepository;
    public UserController(UserService userService, JwtGeneratorInterface jwtGenerator, UserRepository userRepository) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
    }

    @GetMapping(name = "/say",value = "/say")
    public String sayHello() {

        return "Hello From Service1";
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidCredentialsException{
        try {
            if(user.getEmail() == null || user.getPassword() == null) {
                throw new RuntimeException("Username or Password are empty");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Optional<User> optionalUser = userService.getUserByEmail(user.getEmail());

                User userData = optionalUser.get();
            System.out.println(userData.getEmail());
            System.out.println(userData.getPassword());
                String hashedPassword = passwordEncoder.encode(user.getPassword());
                boolean isPasswordMatch = passwordEncoder.matches(userData.getPassword(), user.getPassword());

            return new ResponseEntity<>(jwtGenerator.generateToken(userData), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");
        jwtGenerator.invalidateToken(token);
        return ResponseEntity.ok("Logout successful");
    }
}