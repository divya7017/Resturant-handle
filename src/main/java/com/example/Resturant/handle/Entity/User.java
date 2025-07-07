package com.example.Resturant.handle.Entity;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@Document(collection = "User")
public class User {

  private ObjectId Id;
  private String username;
  private String password;
  private String email;
  private String role;
  private LocalDate signupDate;
}
