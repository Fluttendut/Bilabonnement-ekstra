package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.LeasingContract;

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

    public Car getACar(String serialnumber) {

        Car car = new Car();
        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars WHERE serialnumber=?");
            psts.setString(1, serialnumber);
            ResultSet resultSet = psts.executeQuery();

            if (resultSet.next()) {
                car = new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type"),
                        resultSet.getBoolean("Damaged"),
                        resultSet.getBoolean("Available"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    public String autoSerialNumber(String type) {

        List<LeasingContract> cars = new ArrayList<>();

        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars WHERE available=true and type =?");
            psts.setString(1, type);
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                cars.add(new LeasingContract(
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type")));
            }
            return cars.get(0).getSerialnumber();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Get all cars
    public List<Car> getAllCars() {

        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars");
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                cars.add(new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type"),
                        resultSet.getBoolean("damaged"),
                        resultSet.getBoolean("available")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cars;
    }

    public List<Car> getAllCarsByType(String type) {

        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars where type=?");
            psts.setString(1, type);
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                cars.add(new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type"),
                        resultSet.getBoolean("damaged"),
                        resultSet.getBoolean("available")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cars;
    }

    public List<Car> getAllDamagedCars() {

        List<Car> damagedCars = new ArrayList<>();
        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars where damaged=1");
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                damagedCars.add(new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type"),
                        resultSet.getBoolean("damaged"),
                        resultSet.getBoolean("available")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return damagedCars;
    }

    public void updateCarDamaged(String serialnumber) throws RuntimeException {
        try {
            PreparedStatement psts = conn.prepareStatement("update bilabonnement.cars set damaged =? where serialnumber=?");
            Car car = getACar(serialnumber);
            if (car.getIsDamaged() == true) {
                psts.setBoolean(1, false);
                psts.setString(2, serialnumber);
                psts.executeUpdate();
            } else {
                psts.setBoolean(1, true);
                psts.setString(2, serialnumber);
                psts.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCarAvailable(String serialnumber) throws RuntimeException {
        try {
            PreparedStatement psts = conn.prepareStatement("update bilabonnement.cars set available=? where serialnumber=?");

            Car car = getACar(serialnumber);
            if (car.getIsAvailable() == false) {
                psts.setBoolean(1, true);
                psts.setString(2, serialnumber);
                psts.executeUpdate();
            } else {
                psts.setBoolean(1, false);
                psts.setString(2, serialnumber);
                psts.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCarDamagePrice(String serialnumber, String priceForCollectiveDamage) throws RuntimeException {
        try {
            PreparedStatement psts = conn.prepareStatement("update bilabonnement.cars set PriceForCollectiveDamage=? where serialnumber=?");

            psts.setString(1, priceForCollectiveDamage);
            psts.setString(2, serialnumber);
            psts.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetDamagePrice(String serialnumber) throws RuntimeException {
        try {
            PreparedStatement psts = conn.prepareStatement("update bilabonnement.cars set PriceForCollectiveDamage=? where serialnumber=?");
            psts.setString(1,"0");
            psts.setString(2,serialnumber);
            psts.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
