package com.example.Resturant.handle.Service;

import com.example.Resturant.handle.Entity.DishItem;
import com.example.Resturant.handle.Entity.Orders;
import com.example.Resturant.handle.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public ResponseEntity<String> newOrder(Orders orders) {
        orders.setOrderId("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        if (orders.getOrders() != null) {
            for (DishItem item : orders.getOrders()) {
                item.setStatus("PENDING"); // also set status for each dish
            }
        }
        orders.setTime(LocalDateTime.now());
        orders.setCreateAt(LocalDateTime.now());
        orders.setUpdateAt(LocalDateTime.now());
        orderRepo.save(orders);
        return ResponseEntity.ok("Order saved successfully");
    }


    public ResponseEntity<String> addNewItem(DishItem dishItem, String orderid) {
          Optional<Orders> order = orderRepo.findByOrderId(orderid);
          Orders order1 = order.get();
          dishItem.setStatus("PENDING");
          order1.setTotalPrice(order1.getTotalPrice()+ dishItem.getPrice()* dishItem.getQuantity());
          List<DishItem> currentItems= order1.getOrders();
          currentItems.add(dishItem);
          order1.setOrders(currentItems);
          order1.setUpdateAt(LocalDateTime.now());
          orderRepo.save(order1);
          return ResponseEntity.ok("Item Add Successfully");
    }

    public ResponseEntity<String> deleteDishItem(String orderId, String dishName) {
        Optional<Orders> optionalOrder = orderRepo.findByOrderId(orderId);
        if (optionalOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
        Orders order = optionalOrder.get();
        List<DishItem> updatedItems = order.getOrders();
        boolean removed = updatedItems.removeIf(item -> item.getDishName().equalsIgnoreCase(dishName));
        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dish not found in order");
        }
        order.setOrders(updatedItems);
        orderRepo.save(order);
        return ResponseEntity.ok("Dish removed successfully from order");
    }

    public List<Orders> getAllItem() {
        return orderRepo.findAll();
    }
}
