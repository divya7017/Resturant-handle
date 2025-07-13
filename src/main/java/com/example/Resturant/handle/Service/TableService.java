package com.example.Resturant.handle.Service;

import com.example.Resturant.handle.Entity.TableBook;
import com.example.Resturant.handle.Repo.TableRepo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.web.servlet.function.ServerResponse.status;

@Service
public class TableService {

    @Autowired
    TableRepo tableRepo;

    public ResponseEntity<String> addTable(TableBook tableBook) {
        if (tableRepo.findByTableNo(tableBook.getTableNo()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Table already exists");
        }

        try {
            tableBook.setTableState("Available");
            tableBook.setCustomerName(null);
            tableBook.setCustomerNumber(null);
            tableBook.setGuestNo(0);

            tableRepo.save(tableBook);
            return ResponseEntity.ok("Table added successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving table: " + e.getMessage());
        }
    }

}
