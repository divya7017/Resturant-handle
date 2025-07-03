package com.example.Resturant.handle.Entity;


import lombok.Data;

@Data
public class DishItem {

    private String dishName;
    private int quantity;
    private double price;
    private String status;

    public DishItem() {
        this.status = "PENDING"; // default status
    }
}
