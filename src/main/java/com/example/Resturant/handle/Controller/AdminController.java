package com.example.Resturant.handle.Controller;

import com.example.Resturant.handle.Entity.User;
import com.example.Resturant.handle.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;


    @GetMapping("/User")
    public List<User> showApprovedUser(){
        return userService.getAllApprovUser();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> newUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }
}
