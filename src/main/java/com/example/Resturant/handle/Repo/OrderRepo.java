package com.example.Resturant.handle.Repo;

import com.example.Resturant.handle.Entity.Orders;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepo extends MongoRepository<Orders, ObjectId> {

    Optional<Orders> findByOrderId(String orderId);
}
