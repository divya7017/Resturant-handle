package com.example.Resturant.handle.Entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Table")
public class TableBook {

    private ObjectId id;
    private final int tableNo;
    private String tableState;
    private String bookedBy;
    private String customerName;
    private String customerNumber;
    private int guestNo;
    private final int chairNo;
    private String orderId;
}
