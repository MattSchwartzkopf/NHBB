package com.example.nhbb.Service;

import com.example.nhbb.Model.InventoryModel;
import com.example.nhbb.Repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DatabaseService {

    public static Connection database;
    public static Statement statement;

    public DatabaseService() throws SQLException, ClassNotFoundException {
        startDatabase();
    }

    public void startDatabase() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:8889/sys"; // Database details
        String username = "root"; // MySQL credentials
        String password = "root";

        // Load and register the driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish connection
        database = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established successfully");
    }

    public static void stopDatabase() throws SQLException {
        // Close the statement and connection
        statement.close();
        database.close();
        System.out.println("Connection Closed....");
    }

    public List<InventoryModel> queryAllFromInventoryTable() throws SQLException {
        String query = "select * from inventory"; // Query to be run
        List<InventoryModel> inventoryModelList = new ArrayList<>();

        // Create a statement
        statement = database.createStatement();

        // Execute the query
        ResultSet rs = statement.executeQuery(query);

        // Process the results
        while (rs.next()) {
            InventoryModel inventoryModel = new InventoryModel();
            int id = Integer.parseInt(rs.getString("id")); // Retrieve name from db
            String name = rs.getString("item"); // Retrieve name from db
            int quantity = rs.getInt("quantity");
            inventoryModel.setId(id);
            inventoryModel.setItem(name);
            inventoryModel.setQuantity(quantity);
            inventoryModelList.add(inventoryModel);
            log.info(String.valueOf(id));
            log.info(name);
            log.info(String.valueOf(quantity));
            System.out.println(name); // Print result on console
        }

        return inventoryModelList;
    }

    public void addItem(InventoryModel item) throws SQLException {
        InventoryRepository inventoryRepositoryItem = new InventoryRepository();
        inventoryRepositoryItem.setItem(String.valueOf(item.getItem()));
        inventoryRepositoryItem.setQuantity(item.getQuantity());

        String query = "INSERT INTO inventory (item, quantity) VALUES(?, ?)"; // Query to be run
        PreparedStatement pstmt = database.prepareStatement(query);
        pstmt.setString(1, item.getItem());
        pstmt.setString(2, String.valueOf(item.getQuantity()));
        pstmt.executeUpdate();
    }
}
