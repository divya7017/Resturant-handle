package com.example.Resturant.handle.Controller;

import com.example.Resturant.handle.Entity.DishItem;
import com.example.Resturant.handle.Entity.Orders;
import com.example.Resturant.handle.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Order")
public class OrderConroller {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody Orders orders){
        return orderService.newOrder(orders);
    }

    @PostMapping("/addNewItem")
    public ResponseEntity<String> addNewItem(@RequestBody DishItem dishItem , @RequestParam String orderid){
        return orderService.addNewItem(dishItem ,orderid);

    }

    @DeleteMapping("/deleteDish")
    public ResponseEntity<String> deleteDishItem(@RequestParam String orderid, @RequestParam String dishName) {
        return orderService.deleteDishItem(orderid, dishName);
    }
}
