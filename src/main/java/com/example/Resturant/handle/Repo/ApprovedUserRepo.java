package com.example.Resturant.handle.Repo;

import com.example.Resturant.handle.Entity.ApprovedUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApprovedUserRepo extends MongoRepository<ApprovedUser, ObjectId> {
    Optional<ApprovedUser>findByUsername(String username);
    void deleteByUsername(String username);
}
