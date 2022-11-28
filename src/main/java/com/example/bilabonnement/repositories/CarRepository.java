package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.Car;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    private Connection conn = DatabaseConnectionManager.getConnection();

    public CarRepository() throws IOException {
    }

    public List<Car> getCar(int serialnumber){

        List<Car> cars = new ArrayList<>();
        try
        {
            // spørgsmålstegnet gør vores query dynamisk i stedet for statisk
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars WHERE serialnumber=? and available=true and damaged=false");
            psts.setInt(1, serialnumber);
            ResultSet resultSet = psts.executeQuery();

            if (resultSet.next())
            {
                cars.add(new Car(
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
        return cars;
    }

}
