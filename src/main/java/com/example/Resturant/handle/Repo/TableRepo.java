package com.example.Resturant.handle.Repo;

import com.example.Resturant.handle.Entity.TableBook;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TableRepo extends MongoRepository<TableBook, ObjectId> {
    Optional<TableBook> findByTableNo(int tableNo);

}
