package com.example.bilabonnement.services;

import com.example.bilabonnement.models.LeasingContract;
import com.example.bilabonnement.repositories.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneOffset;

public class RentService {

    private Connection conn = DatabaseConnectionManager.getConnection();

    public RentService() throws IOException {
    }

    public void createRentalContract(LeasingContract leasingContract){
        try
        {
            PreparedStatement psts = conn.prepareStatement("insert into bilabonnement.leasing(type, price, startdate,enddate,serialnumber) values(?,?,?,?,?)"); // spørgsmålstegnet gør vores querry dynamisk i stedet for statisk
            psts.setString(1, leasingContract.getType());
            psts.setInt(2,leasingContract.getPrice());
            psts.setDate(3, (Date) Date.from(leasingContract.getStartdate().toInstant(ZoneOffset.UTC)));
            psts.setDate(3, (Date) Date.from(leasingContract.getEnddate().toInstant(ZoneOffset.UTC)));
            psts.setInt(5,leasingContract.getSerialnumber());


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
