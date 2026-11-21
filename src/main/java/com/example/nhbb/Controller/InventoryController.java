package com.example.nhbb.Controller;

import com.example.nhbb.Model.InventoryModel;
import com.example.nhbb.Service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/getInventory")
    public List<InventoryModel> getCurrentInventory() throws SQLException {
        return inventoryService.getAllInventory();
    }

    @PostMapping("/addItem")
    public ResponseEntity<String> addItem(@RequestBody InventoryModel inventoryModel) throws SQLException {
        inventoryService.addInventoryItem(inventoryModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Added item: " + inventoryModel.getItem() + " " + inventoryModel.getQuantity());
    }
}
