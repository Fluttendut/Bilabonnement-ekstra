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
                        resultSet.getBoolean("isRentedOut")));
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return cars;
    }

    // Get all cars
    public List<Car> getAllCars()
    {

        List<Car> cars = new ArrayList<>();
        try
        {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars");
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next())
            {
                cars.add(new Car(
                                resultSet.getInt("id"),
                                resultSet.getInt("serialnumber"),
                                resultSet.getString("type"),
                                resultSet.getInt("price"),
                                resultSet.getBoolean("isDamaged"),
                                resultSet.getBoolean("isAvailable"),
                                resultSet.getBoolean("isRentedOut")));
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return cars;
    }


    // Creates and insert new car object into DB
    public void createWish(Car car) throws RuntimeException
    {

        try
        {
            PreparedStatement psts = conn.prepareStatement("insert into bilabonnement.cars(serialnumber, type, price, damaged, available) values(?,?,?,?,?)"); // spørgsmålstegnet gør vores querry dynamisk i stedet for statisk
            psts.setInt(1,car.getSerialnumber());
            psts.setString(2, car.getType());
            psts.setInt(3,car.getPrice());
            psts.setBoolean(4, car.isDamaged());
            psts.setBoolean(5, car.isAvailable());

            psts.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    // Deletes car object from DB
    public void deleteWish(int serialnumber) throws RuntimeException
    {
        try
        {
            PreparedStatement psts = conn.prepareStatement("delete from bilabonnement.cars where serialnumber=? ");
            psts.setInt(1,serialnumber);
            psts.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
