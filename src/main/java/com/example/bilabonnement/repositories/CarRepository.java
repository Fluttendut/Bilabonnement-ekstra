package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.Car;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRepository {

    private Connection conn = DatabaseConnectionManager.getConnection();

    public CarRepository() throws IOException {
    }

    public Car getCar(int serialnumber){
        try
        {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars WHERE serialnumber=?"); // spørgsmålstegnet gør vores querry dynamisk i stedet for statisk
            psts.setInt(1, serialnumber);
            ResultSet resultSet = psts.executeQuery();

            if (resultSet.next())
            {
                return new Car(
                        resultSet.getInt("id"),
                        resultSet.getInt("serialnumber"),
                        resultSet.getString("type"),
                        resultSet.getInt("price"),
                        resultSet.getBoolean("isDamaged"),
                        resultSet.getBoolean("isAvailable"),
                        resultSet.getBoolean("isRentedOut"));
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }
}
