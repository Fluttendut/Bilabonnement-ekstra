package com.example.bilabonnement.repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    //variables
    private static String hostname;
    private static String username;
    private static String password;
    private static Connection conn;

    //methods
    public static Connection getConnection() throws IOException
    {
        //this is where it finds the inputs used further down. this can be found in the resources folder (application.properties)
        InputStream propertiesFiles = new FileInputStream("src/main/resources/application.properties");

        //this part of the code tries to verify the link to the database and the login attributes (username and password )
        Properties properties = new Properties();
        properties.load(propertiesFiles);
        hostname = properties.getProperty("spring.datasource.url");
        username = properties.getProperty("spring.datasource.username");
        password = properties.getProperty("spring.datasource.password");

        //this is the part of the code that tries connect to the server in this project placed on the azure cloud solutions application.
        try
        {
            conn = DriverManager.getConnection(hostname,username, password);
        }
        catch (SQLException e){
            //sout placed here to tell us if we succeeded in connecting
            System.out.println("connection to database failed!");
            throw new RuntimeException(e);
        }
        //sout placed here to tell us if we succeeded in connecting
        System.out.println("connection to database successful ");
        return conn;
    }
}
