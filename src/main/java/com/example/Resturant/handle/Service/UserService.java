package com.example.Resturant.handle.Service;

import com.example.Resturant.handle.Entity.LogInRequest;
import com.example.Resturant.handle.Entity.User;
import com.example.Resturant.handle.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void createNewUser(User user){
        user.setPassword(pe.encode(user.getPassword()));
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("userName has already taken");
        }else{
            userRepo.save(user);
        }
    }

    public List<User> getAllApprovUser(){
        return userRepo.findAll();
    }





}

