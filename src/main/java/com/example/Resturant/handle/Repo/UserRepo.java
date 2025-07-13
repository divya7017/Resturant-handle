package com.example.Resturant.handle.Repo;

import com.example.Resturant.handle.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    Optional<User> findByUsername(String username);
    int deleteByUsername(String username);

}
