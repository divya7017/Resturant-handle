package com.example.Resturant.handle.Controller;


import com.example.Resturant.handle.Entity.ApprovedUser;
import com.example.Resturant.handle.Entity.LogInRequest;
import com.example.Resturant.handle.Entity.User;
import com.example.Resturant.handle.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String newUser(@RequestBody User user) {
        userService.createNewUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public void loginrequest(@RequestBody LogInRequest request){
        userService.login(request);
    }


    @GetMapping("/User")
    public List<ApprovedUser> showApprovedUser(){
        return userService.getAllApprovUser();
    }

    @GetMapping("/unapproved")
    public List<User> showUnapprovUser(){
        return userService.getAllUnapprovUser();
    }

    @PostMapping("/unapproved_to_approved")
    public ResponseEntity<String> unapprovedToApproved(@RequestBody Map<String, String> request){
        String username = request.get("username");
        userService.unapprovedtoapproved(username);
        return ResponseEntity.ok("Approved Done");
    }

    @PostMapping("/approved_to_unapproved")
    public ResponseEntity<String> approvedToUnapproved(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        ResponseEntity<String> response = userService.approvedtounapproved(username);

        // Print to terminal (console)
        System.out.println("Response: " + response.getStatusCode() + " - " + response.getBody());

        return response;
    }

}
