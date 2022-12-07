package com.example.bilabonnement.services;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.LeasingContract;
import com.example.bilabonnement.models.Rentee;
import com.example.bilabonnement.repositories.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class RentService {

    private Connection conn = DatabaseConnectionManager.getConnection();

    Car car = new Car();

    public RentService() throws IOException {
    }


    public void createRentalContract(LeasingContract leasingContract, Rentee rentee){
        try
        {
            PreparedStatement psts = conn.prepareStatement("insert into bilabonnement.leasing(type, startdate,enddate,serialnumber, priceMonthly, priceTotal,leasingperiod,name, Email, CPR,address) values(?,?,?,?,?,?,?,?,?,?,?)"); // spørgsmålstegnet gør vores querry dynamisk i stedet for statisk
            psts.setString(1, leasingContract.getType());
            psts.setString(2, leasingContract.getStartdate()); //todo spørg philip om hvordan man får datoen ind fra HTML/JS
            psts.setString(3,leasingContract.getEnddate());
            psts.setInt(4,leasingContract.getSerialnumber());
            psts.setInt(5, leasingContract.getPriceMonthly());
            psts.setInt(6,leasingContract.getPriceTotal());
            psts.setInt(7,leasingContract.getLeasingperiod());
            psts.setString(8, rentee.getName());
            psts.setString(9, rentee.getEmail());
            psts.setString(10,rentee.getCpr());
            psts.setString(11,rentee.getAddress());


            psts.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }



    public void cancelRentalContract(int serialnumber){
        try
        {
            PreparedStatement psts = conn.prepareStatement("delete from bilabonnement.leasing where serialnumber=? ");
            psts.setInt(1,serialnumber);
            psts.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }



}
