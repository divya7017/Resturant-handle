package com.example.Resturant.handle.Service;

import com.example.Resturant.handle.Entity.ApprovedUser;
import com.example.Resturant.handle.Entity.LogInRequest;
import com.example.Resturant.handle.Entity.User;
import com.example.Resturant.handle.Repo.ApprovedUserRepo;
import com.example.Resturant.handle.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ApprovedUserRepo approvedUserRepo;


    @Autowired
    private PasswordEncoder pe;

    public void createNewUser(User user){
        user.setPassword(pe.encode(user.getPassword()));
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("userName has already taken");
        }else{
            userRepo.save(user);
        }
    }

    public ResponseEntity<String> login(LogInRequest logInRequest){

        Optional<ApprovedUser> optionalUser = approvedUserRepo.findByUsername(logInRequest.getUsername());

        if(optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
        }

        ApprovedUser user = optionalUser.get();
        boolean isPasswordMatch= pe.matches(logInRequest.getPassword(),user.getPassword());

        if(!isPasswordMatch){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        return ResponseEntity.ok("Login successful");
    }

    public List<ApprovedUser> getAllApprovUser(){
        return approvedUserRepo.findAll();
    }

    public List<User> getAllUnapprovUser(){
        return userRepo.findAll();
    }

    public ResponseEntity<String> unapprovedtoapproved(String username){
        Optional<User> user = userRepo.findByUsername(username);
        User unapprovedUser = user.get();

        ApprovedUser approvedUser = new ApprovedUser();
        approvedUser.setId(unapprovedUser.getId());
        approvedUser.setUsername(unapprovedUser.getUsername());
        approvedUser.setPassword(unapprovedUser.getPassword());
        approvedUser.setEmail(unapprovedUser.getEmail());
        approvedUser.setRole(unapprovedUser.getRole());
        approvedUserRepo.save(approvedUser);

        userRepo.deleteByUsername(username);
        return ResponseEntity.ok("Approved Done");
    }

    public ResponseEntity<String> approvedtounapproved(String username){
        Optional<ApprovedUser> user = approvedUserRepo.findByUsername(username);
        ApprovedUser unapprovedUser = user.get();

        String role =unapprovedUser.getRole();

        if ("ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot unapprove Admin user");
        }else{
            User approvedUser = new User();
            approvedUser.setId(unapprovedUser.getId());
            approvedUser.setUsername(unapprovedUser.getUsername());
            approvedUser.setPassword(unapprovedUser.getPassword());
            approvedUser.setEmail(unapprovedUser.getEmail());
            approvedUser.setRole(unapprovedUser.getRole());
            userRepo.save(approvedUser);

            approvedUserRepo.deleteByUsername(username);
            return ResponseEntity.ok("Unapproved Done");
        }
    }
}
