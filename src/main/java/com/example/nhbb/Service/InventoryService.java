package com.example.nhbb.Service;

import com.example.nhbb.Model.InventoryModel;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class InventoryService {

    private final DatabaseService databaseService;

    public InventoryService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<InventoryModel> getAllInventory() throws SQLException {
        return databaseService.queryAllFromInventoryTable();
    }

    public void addInventoryItem(InventoryModel inventoryModel) throws SQLException {
        databaseService.addInventoryItem(inventoryModel);
    }

    public void deleteInventoryItem(String itemName, int quantity) throws SQLException {
        databaseService.deleteItemFromInventory(itemName, quantity);
    }
}
