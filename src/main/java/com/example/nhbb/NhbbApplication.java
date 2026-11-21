package com.example.nhbb;

import com.example.nhbb.Repository.InventoryRepository;
import com.example.nhbb.Service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@Slf4j
@SpringBootApplication
public class NhbbApplication {

//    public static Connection database;
//    public static Statement statement;

    private final DatabaseService databaseService;

    public NhbbApplication(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(NhbbApplication.class, args);

        // Start the database
//        databaseService.startDatabase();
//        connectDb();
//        log.info("Read entire database");
//        queryAllDatabase();
//        testAddDatabase();
//        log.info("Read entire database after item addition");
//        queryAllDatabase();
//        stopDatabase();
    }

//    public static void testAddDatabase() throws SQLException {
//        InventoryRepository item = new InventoryRepository();
//        item.setItem("Crankshaft");
//        item.setQuantity(2);
//
//        addToDatabase(item);
//    }

//    public static void connectDb() throws ClassNotFoundException, SQLException {
//        String url = "jdbc:mysql://localhost:8889/sys"; // Database details
//        String username = "root"; // MySQL credentials
//        String password = "root";
//
//        // Load and register the driver
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        // Establish connection
//        database = DriverManager.getConnection(url, username, password);
//        System.out.println("Connection Established successfully");
//    }
//
//    public static void stopDatabase() throws SQLException {
//        // Close the statement and connection
//        statement.close();
//        database.close();
//        System.out.println("Connection Closed....");
//    }
//
//    public static void queryAllDatabase() throws SQLException {
//        String query = "select * from inventory"; // Query to be run
//
//        // Create a statement
//        statement = database.createStatement();
//
//        // Execute the query
//        ResultSet rs = statement.executeQuery(query);
//
//        // Process the results
//        while (rs.next()) {
//            String name = rs.getString("item"); // Retrieve name from db
//            String quantity = rs.getString("quantity");
//            log.info(name);
//            log.info(quantity);
//            System.out.println(name); // Print result on console
//        }
//    }
//
//    public static void addToDatabase(InventoryRepository item) throws SQLException {
//        InventoryRepository inventoryRepositoryItem = new InventoryRepository();
//        inventoryRepositoryItem.setItem(item.getItem());
//        inventoryRepositoryItem.setQuantity(item.getQuantity());
//
//        String query = "INSERT INTO inventory (item, quantity) VALUES(?, ?)"; // Query to be run
//        PreparedStatement pstmt = database.prepareStatement(query);
//        pstmt.setString(1, item.getItem());
//        pstmt.setString(2, String.valueOf(item.getQuantity()));
//        pstmt.execute();
//    }
}
