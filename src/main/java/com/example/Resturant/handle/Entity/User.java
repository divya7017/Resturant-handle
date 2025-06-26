package com.example.Resturant.handle.Entity;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivateKey;

@Data
public class User {

  private ObjectId Id;
  private String username;
  private String password;
  private String email;
  private String role;
}
