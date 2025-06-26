package com.example.Resturant.handle.Entity;


import lombok.Data;
import org.springframework.boot.context.event.SpringApplicationEvent;

@Data
public class LogInRequest {
    private String username;
    private String password;
}
