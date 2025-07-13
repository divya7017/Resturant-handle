package com.example.Resturant.handle.Service;

import com.example.Resturant.handle.Entity.LogInRequest;
import com.example.Resturant.handle.Entity.User;
import com.example.Resturant.handle.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder pe;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    public ResponseEntity<String> createNewUser(User user) {
        user.setPassword(pe.encode(user.getPassword()));

        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken");
        } else {
            user.setSignupDate(LocalDate.now());
            userRepo.save(user);
            return ResponseEntity.ok("Signup successful");
        }
    }


    public List<User> getAllApprovUser(){

        return userRepo.findAll();
    }


    @Transactional
    public ResponseEntity<String> deleteUser(String username) {
        int result = userRepo.deleteByUsername(username);
        if (result > 0) {
            return ResponseEntity.ok("username Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unable to delete username");
        }
    }



}

