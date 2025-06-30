package com.example.Resturant.handle.Repo;

import com.example.Resturant.handle.Entity.MenuItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MenuRepo extends MongoRepository<MenuItem, ObjectId> {

    Optional<MenuItem> findByName(String name);
    void deleteByName(String name);
}
