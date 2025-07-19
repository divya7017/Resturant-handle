package com.example.Resturant.handle.Controller;

import com.example.Resturant.handle.Entity.TableBook;
import com.example.Resturant.handle.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    TableService tableService;


    @PostMapping("/admin/addTable")
    public ResponseEntity<String> addTable(@RequestBody TableBook tableBook){
        return tableService.addTable(tableBook);
    }


    @GetMapping("/worker/allTable")
    public List<TableBook> showAllTable(){
       return tableService.showTables();
    }

    @PutMapping("/worker/editTable/{tableNo}")
    public ResponseEntity<String> editTable(@PathVariable int tableNo, @RequestBody TableBook tableBook) {
        return tableService.editTable(tableNo, tableBook);
    }


}
