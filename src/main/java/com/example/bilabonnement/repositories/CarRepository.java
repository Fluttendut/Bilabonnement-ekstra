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

    //Method that returns a Car object by serialnumber
    public Car getACar(String serialnumber) {

        //Creating a new object of Car
        Car car = new Car();
        //We use Try to run the code to see if the code will run
        try {
            //We prepare a select SQL statement where we find all cars from bilabonnement where the serialnumber is the paramenter
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars WHERE serialnumber=?");
            //We set the placeholder ? on parameterIndex 1 to be a serialnumber
            psts.setString(1, serialnumber);
            //We execute the SQL statement
            ResultSet resultSet = psts.executeQuery();

            if (resultSet.next()) {
                car = new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type"),
                        resultSet.getBoolean("Damaged"),
                        resultSet.getBoolean("Available"));
            }
        //If the try fails, we will catch it here with an exception so that we can see what went wrong
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //We return the Car object we have found with this method
        return car;
    }

    public String autoSerialNumber(String type) {

        List<LeasingContract> cars = new ArrayList<>();

        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars WHERE available=true and type =?");
            psts.setString(1, type);
            ResultSet resultSet = psts.executeQuery();
            //this is a while statement used to find an available car, create leasingContract object with the attributes serialnumber and type
            while (resultSet.next()) {
                cars.add(new LeasingContract(
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type")));
            }
            //return the serialnumber on index 0 of an object in the "cars" list
            return cars.get(0).getSerialnumber();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Get all cars
    //Method that returns a list of all Car objects regardless of their status
    public List<Car> getAllCars() {

        List<Car> cars = new ArrayList<>();
        //We use Try to run the code to see if the code will run
        try {
            //We prepare a select SQL statement where we find all cars from bilabonnement regardless of their status
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars");
            //We execute the SQL statement
            ResultSet resultSet = psts.executeQuery();
            //this is a while statement used to create new car objects with our Car attributes and return them
            while (resultSet.next()) {
                cars.add(new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type"),
                        resultSet.getBoolean("damaged"),
                        resultSet.getBoolean("available")
                ));
            }
          //If the try fails, we will catch it here with an exception so that we can see what went wrong
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //We return the list of Car objects we have found with this method
        return cars;
    }
    //Method that returns a Car object by serialnumber
    public List<Car> getAllCarsByType(String type) {

        //Creating a list of Car objects
        List<Car> cars = new ArrayList<>();
        //We use Try to run the code to see if the code will run
        try {
            //We prepare a select SQL statement where we find all cars from bilabonnement where the type is the paramenter
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars where type=?");
            //We set the placeholder ? on parameterIndex 1 to be a type
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
            //If the try fails, we will catch it here with an exception so that we can see what went wrong
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //We return the list of Car objects we have found with this method
        return cars;
    }

    //this method returns a list of damaged car objects
    public List<Car> getAllDamagedCars() {

        List<Car> damagedCars = new ArrayList<>();
        //We use Try to run the code to see if the code will run
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
          //If the try fails, we will catch it here with an exception so that we can see what went wrong
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //We return the list of Car objects we have found with this method
        return damagedCars;
    }

    public void updateCarDamaged(String serialnumber) throws RuntimeException {
        //We use Try to run the code to see if the code will run
        try {
            PreparedStatement psts = conn.prepareStatement("update bilabonnement.cars set damaged =? where serialnumber=?");
            Car car = getACar(serialnumber);
            //if the car is damaged it sets the value to false therefore the car is now repaired
            if (car.getIsDamaged() == true) {
                psts.setBoolean(1, false);
                psts.setString(2, serialnumber);
                psts.executeUpdate();
            } else {
                psts.setBoolean(1, true);
                psts.setString(2, serialnumber);
                //We execute the SQL statement and update the table
                psts.executeUpdate();
            }
          //If the try fails, we will catch it here with an exception so that we can see what went wrong  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //this method updates the Available attribute of a car in our database
    public void updateCarAvailable(String serialnumber) throws RuntimeException {
        //We use Try to run the code to see if the code will run
        try {
            PreparedStatement psts = conn.prepareStatement("update bilabonnement.cars set available=? where serialnumber=?");

            Car car = getACar(serialnumber);
            //if the car is damaged it sets the value to false therefore the car is now available
            if (car.getIsAvailable() == false) {
                psts.setBoolean(1, true);
                psts.setString(2, serialnumber);
                //We execute the SQL statement and update the table
                psts.executeUpdate();
                //else method that switches the value of the attribute from true to false and the car is now not available
            } else {
                psts.setBoolean(1, false);
                psts.setString(2, serialnumber);
                //We execute the SQL statement and update the table
                psts.executeUpdate();
            }
          //If the try fails, we will catch it here with an exception so that we can see what went wrong
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //this method updates the damagePrice attribute of a car selected from serialnumber in our database
    public void updateCarDamagePrice(String serialnumber, String priceForCollectiveDamage) throws RuntimeException {
        //We use Try to run the code to see if the code will run
        try {
            PreparedStatement psts = conn.prepareStatement("update bilabonnement.cars set PriceForCollectiveDamage=? where serialnumber=?");

            psts.setString(1, priceForCollectiveDamage);
            psts.setString(2, serialnumber);
            //We execute the SQL statement and update the table
            psts.executeUpdate();
          //If the try fails, we will catch it here with an exception so that we can see what went wrong
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //this method updates the damagePrice attribute of a car selected from serialnumber in our database, and sets it to "0"
    public void resetDamagePrice(String serialnumber) throws RuntimeException {
        //We use Try to run the code to see if the code will run
        try {
            PreparedStatement psts = conn.prepareStatement("update bilabonnement.cars set PriceForCollectiveDamage=? where serialnumber=?");
            // Resets the damageprice to "0"
            psts.setString(1,"0");
            psts.setString(2,serialnumber);
            //We execute the SQL statement and update the table
            psts.executeUpdate();
          //If the try fails, we will catch it here with an exception so that we can see what went wrong
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
