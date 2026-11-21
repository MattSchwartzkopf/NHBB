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
    private final String NEW_ADD_ITEM_QUERY = "SELECT id, item, quantity FROM inventory WHERE item = ?";
    private final String UPDATE_ITEM_QUANTITY_QUERY = "UPDATE inventory SET quantity = quantity + ? WHERE item = ?";

    private final String SELECT_ALL_INVENTORY_QUERY = "select * from inventory";
    private final String DATABASE_CONNECTION_URL = "jdbc:mysql://localhost:8889/sys";
    private final String DATABASE_CONNECTION_USERNAME = "root";
    private final String DATABASE_CONNECTION_PASSWORD = "root";
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public DatabaseService() throws SQLException, ClassNotFoundException {
        startDatabase();
    }

    public void startDatabase() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        database = DriverManager.getConnection(DATABASE_CONNECTION_URL, DATABASE_CONNECTION_USERNAME, DATABASE_CONNECTION_PASSWORD);
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

    public void addInventoryItem(InventoryModel item) throws SQLException {
        InventoryRepository inventoryRepositoryItem = new InventoryRepository();
        inventoryRepositoryItem.setItem(String.valueOf(item.getItem()));
        inventoryRepositoryItem.setQuantity(item.getQuantity());

        // If item doesn't already exists, add new item
        if (!doesInventoryContainItem(item)) {
            addNewItemAndQuantity(item);
        } else { // item exists, update quantity on that item
            updateItemQuantity(item);
        }
    }

    public void addNewItemAndQuantity(InventoryModel item) throws SQLException {
        PreparedStatement preparedStatement = database.prepareStatement(ADD_ITEM_QUERY);
        preparedStatement.setString(1, item.getItem());
        preparedStatement.setString(2, String.valueOf(item.getQuantity()));
        preparedStatement.executeUpdate();
    }

    public void updateItemQuantity(InventoryModel inventoryModel) throws SQLException {
        PreparedStatement preparedStatement = database.prepareStatement(UPDATE_ITEM_QUANTITY_QUERY);
        preparedStatement.setInt(1, inventoryModel.getQuantity());
        preparedStatement.setString(2, inventoryModel.getItem());
        preparedStatement.executeUpdate();
    }

    public boolean doesInventoryContainItem(InventoryModel inventoryModel) throws SQLException {
        statement = database.createStatement();
        PreparedStatement preparedStatement = database.prepareStatement(NEW_ADD_ITEM_QUERY);
        preparedStatement.setString(1, inventoryModel.item);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }
}
