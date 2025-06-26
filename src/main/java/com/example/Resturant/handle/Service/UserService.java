package com.example.Resturant.handle.Service;

import com.example.Resturant.handle.Entity.LogInRequest;
import com.example.Resturant.handle.Entity.User;
import com.example.Resturant.handle.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private PasswordEncoder pe;

    public void createNewUser(User user){
        user.setLocked(true);
        user.setPassword(pe.encode(user.getPassword()));
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("userName has already taken");
        }else{
            userRepo.save(user);
        }
    }

    public ResponseEntity<String> login(LogInRequest logInRequest){

        Optional<User> optionalUser = userRepo.findByUsername(logInRequest.getUsername());

        if(optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
        }

        User user = optionalUser.get();
        boolean isPasswordMatch= pe.matches(logInRequest.getPassword(),user.getPassword());

        if(!isPasswordMatch){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        return ResponseEntity.ok("Login successful");
    }
}
