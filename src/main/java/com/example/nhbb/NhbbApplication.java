package com.example.nhbb;

import com.example.nhbb.Service.DatabaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NhbbApplication {

    private final DatabaseService databaseService;

    public NhbbApplication(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public static void main(String[] args) {
        SpringApplication.run(NhbbApplication.class, args);
    }
}
