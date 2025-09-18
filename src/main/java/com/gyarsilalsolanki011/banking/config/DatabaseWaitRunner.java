package com.gyarsilalsolanki011.banking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseWaitRunner implements CommandLineRunner {
    @Value("${spring.datasource.url}") private String url;
    @Value("${spring.datasource.username}") private String user;
    @Value("${spring.datasource.password}") private String pass;

    @Override
    public void run(String... args) throws Exception {
        boolean connected = false;
        while(!connected) {
            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                connected = true;
            } catch (SQLException e) {
                System.out.println("Waiting for MySQL...");
                Thread.sleep(2000);
            }
        }
    }
}

