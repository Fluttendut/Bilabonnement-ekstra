package com.example.bilabonnement.services;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.LeasingContract;
import com.example.bilabonnement.models.Rentee;
import com.example.bilabonnement.repositories.CarRepository;
import com.example.bilabonnement.repositories.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentService {

    private Connection conn = DatabaseConnectionManager.getConnection();

    //Car car = new Car();

    public RentService() throws IOException {
    }

    public void createRentalContract(LeasingContract leasingContract, Rentee rentee) {
        try {
            CarRepository carRepository = new CarRepository();


            PreparedStatement psts = conn.prepareStatement("insert into bilabonnement.leasing(type, startdate,enddate,serialnumber, priceMonthly, priceTotal,leasingperiod,name, Email, CPR,address) values(?,?,?,?,?,?,?,?,?,?,?)"); // spørgsmålstegnet gør vores querry dynamisk i stedet for statisk
            psts.setString(1, leasingContract.getType());
            psts.setString(2, leasingContract.getStartdate());
            psts.setString(3, leasingContract.getEnddate());

            if (leasingContract.getSerialnumber().equals("")) {
                psts.setString(4, carRepository.autoSerialNumber(leasingContract.getType()));
                carRepository.updateCarAvailable(carRepository.autoSerialNumber(leasingContract.getType()));
            } else {
                psts.setString(4, leasingContract.getSerialnumber());
                carRepository.updateCarAvailable(leasingContract.getSerialnumber());
            }

            psts.setInt(5, leasingContract.getPriceMonthly());
            psts.setInt(6, leasingContract.getPriceTotal());
            psts.setInt(7, leasingContract.getLeasingperiod());
            psts.setString(8, rentee.getName());
            psts.setString(9, rentee.getEmail());
            psts.setString(10, rentee.getCpr());
            psts.setString(11, rentee.getAddress());

            psts.executeUpdate();

        }
     catch(SQLException | IOException e)
    {
        throw new RuntimeException(e);
    }
}


    public void cancelRentalContract(int serialnumber) {
        try {
            PreparedStatement psts = conn.prepareStatement("delete from bilabonnement.leasing where serialnumber=? ");
            psts.setInt(1, serialnumber);
            psts.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
