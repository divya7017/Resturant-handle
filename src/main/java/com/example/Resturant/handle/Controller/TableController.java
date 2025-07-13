package com.example.Resturant.handle.Controller;

import com.example.Resturant.handle.Entity.TableBook;
import com.example.Resturant.handle.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    TableService tableService;


    @PostMapping("/admin/addTable")
    public ResponseEntity<String> addTable(@RequestBody TableBook tableBook){
        return tableService.addTable(tableBook);
    }


}
