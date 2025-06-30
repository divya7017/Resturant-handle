package com.example.Resturant.handle.Service;

import com.example.Resturant.handle.Controller.MenuController;
import com.example.Resturant.handle.Entity.MenuItem;
import com.example.Resturant.handle.Repo.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    MenuRepo menuRepo;

    public ResponseEntity<String> addMenuItem(MenuItem menuItem) {
        if(menuRepo.findByName(menuItem.getName()).isPresent()){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("name already present");
        }else {
            menuRepo.save(menuItem);
            return ResponseEntity.ok("Add Successfully");
        }
    }
}
