package com.example.Resturant.handle.Entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Table")
public class TableBook {

    private ObjectId id;
    private int tableNo;
    private String tableState;
    private String customerName;
    private String customerNumber;
    private int guestNo;
    private int chairNo;
}
