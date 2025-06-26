package com.example.Resturant.handle.Controller;


import com.example.Resturant.handle.Entity.LogInRequest;
import com.example.Resturant.handle.Entity.User;
import com.example.Resturant.handle.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
