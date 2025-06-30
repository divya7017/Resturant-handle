package com.example.Resturant.handle.Service;

import com.example.Resturant.handle.Controller.MenuController;
import com.example.Resturant.handle.Entity.MenuItem;
import com.example.Resturant.handle.Repo.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    MenuRepo menuRepo;

    public ResponseEntity<String> addMenuItem(MenuItem menuItem) {
        Optional<MenuItem> existingItem = menuRepo.findByName(menuItem.getName());

        if (existingItem.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // More appropriate than UNAUTHORIZED
                    .body("Menu item with this name already exists.");
        }

        menuRepo.save(menuItem);
        return ResponseEntity.ok("Menu item added successfully.");
    }

    public ResponseEntity<String> menuPriceChange(String name,String fullNewPrice) {
        Optional<MenuItem> menuItem = menuRepo.findByName(name);

        MenuItem items = new MenuItem();
        items.setId(menuItem.get().getId());
        items.setName(menuItem.get().getName());
        items.setCategory(menuItem.get().getCategory());
        items.setDiscription(menuItem.get().getDiscription());
        items.setFullprice(fullNewPrice);
        items.setHalfprice(menuItem.get().getHalfprice());

        menuRepo.save(items);

        return ResponseEntity.ok("Update Successfully");
    }

    public ResponseEntity<String> menuHalfPriceChange(String name,String fullNewPrice) {
        Optional<MenuItem> menuItem = menuRepo.findByName(name);

        MenuItem items = new MenuItem();
        items.setId(menuItem.get().getId());
        items.setName(menuItem.get().getName());
        items.setCategory(menuItem.get().getCategory());
        items.setDiscription(menuItem.get().getDiscription());
        items.setFullprice(fullNewPrice);
        items.setHalfprice(menuItem.get().getHalfprice());

        menuRepo.save(items);

        return ResponseEntity.ok("Update Successfully");
    }

    public List<MenuItem> showAllItem() {
        return menuRepo.findAll();
    }

    public ResponseEntity<String> deleteMenuItem(String name) {
        menuRepo.deleteByName(name);
        return ResponseEntity.ok("Delete Successfully");
    }
}
