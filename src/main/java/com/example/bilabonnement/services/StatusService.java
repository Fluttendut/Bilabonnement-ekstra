package com.example.bilabonnement.services;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.repositories.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusService {

    private Connection conn = DatabaseConnectionManager.getConnection();

    public StatusService() throws IOException {
    }

    /*
    //TODO create rentedOUT and isDamaged
    public List<Car> damageStatus (int serialnumber){

        List<Car> cars = new ArrayList<>();
        try
        {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.cars WHERE serialnumber=?");
            psts.setInt(1, serialnumber);
            ResultSet resultSet = psts.executeQuery();

            if (resultSet.next())
            {
                cars.add(new Car(
                        resultSet.getBoolean("isDamaged")
                        ));
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return cars;
    }

     */

    public String carAvailability(boolean isRentedOut,boolean isDamaged, int serialnumber) {
        if (isRentedOut == true || isDamaged == true) {
            return "YOU SHALL NOT PASS!!";
        }
        //TODO add car to return
        else { return "";}
    }
    //if(isDamaged == true || rentedOut == true) {
        //return: "Car is not available."; }
    //else { return: Car.****;


    public void damageStatus(boolean isDamaged, int serialnumber){
        //TODO Insert button logic where car status is set to damaged
    }

}
