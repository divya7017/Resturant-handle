package com.example.Resturant.handle.Entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "MenuItem")
public class MenuItem {

    private ObjectId id;
    private String category;
    private String name;
    private String discription;
    private String price;
}
