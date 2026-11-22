package com.example.nhbb.Controller;

import com.example.nhbb.Model.InventoryModel;
import com.example.nhbb.Service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping(value = "/deleteItem")
    public ResponseEntity<String> deleteItem(@RequestParam String itemName, @RequestParam int quantity) throws SQLException {
        inventoryService.deleteInventoryItem(itemName, quantity);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successful!");
    }
}
