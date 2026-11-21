package com.example.nhbb.Service;

import com.example.nhbb.Model.InventoryModel;
import com.example.nhbb.Repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {

    public static Connection database;
    public static Statement statement;

    private final String ADD_ITEM_QUERY = "INSERT INTO inventory (item, quantity) VALUES(?, ?)";
    private final String SELECT_ALL_INVENTORY_QUERY = "select * from inventory";

    public DatabaseService() throws SQLException, ClassNotFoundException {
        startDatabase();
    }

    public void startDatabase() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:8889/sys";
        String username = "root";
        String password = "root";

        // Load and register the driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish connection
        database = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established successfully");
    }

    public List<InventoryModel> queryAllFromInventoryTable() throws SQLException {
        List<InventoryModel> inventoryModelList = new ArrayList<>();

        // Create & execute a statement
        statement = database.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_ALL_INVENTORY_QUERY);

        // Process the results
        while (rs.next()) {
            InventoryModel inventoryModel = new InventoryModel();
            int id = Integer.parseInt(rs.getString("id"));
            String name = rs.getString("item");
            int quantity = rs.getInt("quantity");
            inventoryModel.setId(id);
            inventoryModel.setItem(name);
            inventoryModel.setQuantity(quantity);
            inventoryModelList.add(inventoryModel);
        }

        return inventoryModelList;
    }

    public void addItem(InventoryModel item) throws SQLException {
        InventoryRepository inventoryRepositoryItem = new InventoryRepository();
        inventoryRepositoryItem.setItem(String.valueOf(item.getItem()));
        inventoryRepositoryItem.setQuantity(item.getQuantity());

        PreparedStatement preparedStatement = database.prepareStatement(ADD_ITEM_QUERY);
        preparedStatement.setString(1, item.getItem());
        preparedStatement.setString(2, String.valueOf(item.getQuantity()));
        preparedStatement.executeUpdate();
    }
}
