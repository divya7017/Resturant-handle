package com.example.Resturant.handle.Controller;


import com.example.Resturant.handle.Entity.LogInRequest;
import com.example.Resturant.handle.Entity.User;
import com.example.Resturant.handle.Repo.UserRepo;
import com.example.Resturant.handle.Service.JWTService;
import com.example.Resturant.handle.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogInRequest loginRequest) {
        User user = userRepo.findByUsername(loginRequest.getUsername()).orElseThrow();

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
