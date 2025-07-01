package com.example.Resturant.handle.Controller;

import com.example.Resturant.handle.Entity.MenuItem;
import com.example.Resturant.handle.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/add")
    public ResponseEntity<String> addMenu(@RequestBody MenuItem menuItem){
        return menuService.addMenuItem(menuItem);
    }

    @GetMapping("/all")
    public List<MenuItem> showMenu(){
        return menuService.showAllItem();
    }

    @DeleteMapping("/deleteItem/{name}")
    public ResponseEntity<String> deleteItem(@PathVariable String name){
        return menuService.deleteMenuItem(name);
    }

    @PatchMapping("/pricechange/{name}")
    public ResponseEntity<String> changeFullPrice(@PathVariable String name , @RequestParam String price){
        return menuService.menuPriceChange(name,price);
    }


}
