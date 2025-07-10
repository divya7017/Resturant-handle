package com.example.Resturant.handle.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.List;

@Data
@Document(collection = "Orders")
public class Orders {

    @Id
    private ObjectId id;

    private String orderId;
    private String employeeId;
    private int tableNo;
    private List<DishItem> orders;
    private double totalPrice;
    private String customer;
    private String phone;
    private String status;
    private String time;
    private String notes;
    private String table;
    private String createAt;
    private String updateAt;
}
