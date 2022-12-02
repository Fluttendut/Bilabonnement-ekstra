package com.example.bilabonnement.repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    //variabler
    private static String hostname;
    private static String username;
    private static String password;
    private static Connection conn;

    //metoder
    public static Connection getConnection() throws IOException
    {
        InputStream propertiesFiles = new FileInputStream("src/main/resources/application.properties");

        Properties properties = new Properties();
        properties.load(propertiesFiles);
        hostname = properties.getProperty("spring.datasource.url");
        username = properties.getProperty("spring.datasource.username");
        password = properties.getProperty("spring.datasource.password");

        try
        {
            conn = DriverManager.getConnection(hostname,username, password);
        }
        catch (SQLException e){
            System.out.println("connection to database failed!");
            throw new RuntimeException(e);
        }
        System.out.println("connection to database successfull ");
        return conn;
    }
}
