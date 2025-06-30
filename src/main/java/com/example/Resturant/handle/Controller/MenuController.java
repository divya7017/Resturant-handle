package com.example.Resturant.handle.Controller;

import com.example.Resturant.handle.Entity.MenuItem;
import com.example.Resturant.handle.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    public ResponseEntity<String> addMenu(@RequestBody MenuItem menuItem){
        return menuService.addMenuItem(menuItem);
    }

}
