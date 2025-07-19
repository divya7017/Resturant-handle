package com.example.Resturant.handle.Service;

import com.example.Resturant.handle.Entity.TableBook;
import com.example.Resturant.handle.Repo.TableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            tableBook.setBookedBy(null);
            tableBook.setGuestNo(0);

            tableRepo.save(tableBook);
            return ResponseEntity.ok("Table added successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving table: " + e.getMessage());
        }
    }

    public List<TableBook> showTables() {
        return tableRepo.findAll();
    }

    public ResponseEntity<String> editTable(int tableNo, TableBook updatedTable) {
        Optional<TableBook> optionalTable = tableRepo.findByTableNo(tableNo);

        if (optionalTable.isPresent()) {
            TableBook existingTable = optionalTable.get();

            // Update only the editable fields
            existingTable.setTableState(updatedTable.getTableState());
            existingTable.setBookedBy(updatedTable.getBookedBy());
            existingTable.setCustomerName(updatedTable.getCustomerName());
            existingTable.setCustomerNumber(updatedTable.getCustomerNumber());
            existingTable.setGuestNo(updatedTable.getGuestNo());

            tableRepo.save(existingTable);
            return ResponseEntity.ok("Table updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Table not found.");
        }
    }

}
