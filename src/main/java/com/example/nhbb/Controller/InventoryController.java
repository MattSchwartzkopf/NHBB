package com.example.nhbb.Controller;

import com.example.nhbb.Model.InventoryModel;
import com.example.nhbb.Service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final DatabaseService databaseService;

    public InventoryController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/getInventory")
    public List<InventoryModel> getCurrentInventory() throws SQLException {
        log.info("Got into getInventory");
        return databaseService.queryAllFromInventoryTable();
    }

    @PostMapping("/addItem")
    public ResponseEntity<String> addItem(@RequestBody InventoryModel inventoryModel) throws SQLException {
        databaseService.addItem(inventoryModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Added item: " + inventoryModel.getItem() + " " + inventoryModel.getQuantity());
    }
}
