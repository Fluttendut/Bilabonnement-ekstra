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


    public Car getAvailableCar(String serialnumber) {

        Car car = new Car();
        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars WHERE serialnumber=? and available=true and damaged=false");
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

    public List<Car> getAllRentedCars() {

        List<Car> rentedCars = new ArrayList<>();
        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars where available=0");
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                rentedCars.add(new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type"),
                        resultSet.getBoolean("isDamaged"),
                        resultSet.getBoolean("isAvailable")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rentedCars;
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

/*
    //TODO SPACCCESHIIIPPPPP EARLY EDITION!!! LIMITED BS!!
       Creates and insert new car object into DB and delete function
    public void createCar(Car car) throws RuntimeException
    {

        try
        {
            PreparedStatement psts = conn.prepareStatement("insert into bilabonnement.cars(serialnumber, type, price, damaged, available) values(?,?,?,?,?)"); // spørgsmålstegnet gør vores querry dynamisk i stedet for statisk
            psts.setInt(1,car.getSerialnumber());
            psts.setString(2, car.getType());
            psts.setInt(3,car.getPrice());
            psts.setBoolean(4, car.getIsDamaged());
            psts.setBoolean(5, car.getIsAvailable());

            psts.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    // Deletes car object from DB
    public void deleteCar(int serialnumber) throws RuntimeException
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
 */

    // updates car object from DB
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

    public int getMonthlyIncome() {

        int totalMonthlyIncome = 0;

        List<LeasingContract> contracts = new ArrayList<>();

        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing");
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                contracts.add(new LeasingContract(
                        resultSet.getString("type"),
                        resultSet.getInt("priceMonthly"),
                        resultSet.getInt("priceTotal"),
                        resultSet.getString("serialnumber"),
                        resultSet.getString("startdate"),
                        resultSet.getString("enddate"),
                        resultSet.getInt("leasingperiod"),
                        resultSet.getInt("contractID")
                ));
            }

            for(int i=0 ; i<contracts.size() ; i++){
                totalMonthlyIncome = totalMonthlyIncome + contracts.get(i).getPriceMonthly();
            }
            return totalMonthlyIncome;


        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAnnualIncome() {

        int annualIncome = 0;
        int monthsInAYear = 12;
        List<LeasingContract> contracts = new ArrayList<>();

        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing");
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                contracts.add(new LeasingContract(
                        resultSet.getString("type"),
                        resultSet.getInt("priceMonthly"),
                        resultSet.getInt("priceTotal"),
                        resultSet.getString("serialnumber"),
                        resultSet.getString("startdate"),
                        resultSet.getString("enddate"),
                        resultSet.getInt("leasingperiod"),
                        resultSet.getInt("contractID")
                ));
            }


            for(int i=0 ; i<contracts.size() ; i++){
                annualIncome = annualIncome + (contracts.get(i).getPriceMonthly() * monthsInAYear);

            }
            System.out.println(annualIncome);
            return annualIncome;


        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }



}
