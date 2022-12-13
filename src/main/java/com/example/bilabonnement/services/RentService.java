package com.example.bilabonnement.services;

import com.example.bilabonnement.models.LeasingContract;
import com.example.bilabonnement.models.Rentee;
import com.example.bilabonnement.repositories.CarRepository;
import com.example.bilabonnement.repositories.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentService {

    private Connection conn = DatabaseConnectionManager.getConnection();

    public RentService() throws IOException {
    }

    public void createRentalContract(LeasingContract leasingContract, Rentee rentee) {
        try {
            CarRepository carRepository = new CarRepository();

            PreparedStatement psts = conn.prepareStatement("insert into bilabonnement.leasing(type, startdate,enddate,serialnumber, priceMonthly, priceTotal,priceAnnual,leasingperiod,name, Email, CPR,address) values(?,?,?,?,?,?,?,?,?,?,?,?)"); // spørgsmålstegnet gør vores querry dynamisk i stedet for statisk
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
            psts.setInt(7,(leasingContract.getPriceMonthly())*12);
            psts.setInt(8, leasingContract.getLeasingperiod());
            psts.setString(9, rentee.getName());
            psts.setString(10, rentee.getEmail());
            psts.setString(11, rentee.getCpr());
            psts.setString(12, rentee.getAddress());

            psts.executeUpdate();


        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void cancelRentalContract(int contractID, int damageCheck, String damagePrice) {
        try {
            CarRepository carRepo = new CarRepository();

            PreparedStatement psts = conn.prepareStatement("delete from bilabonnement.leasing where contractID=?");
            psts.setInt(1, contractID);
            carRepo.updateCarAvailable(getSerialFromContractID(contractID));
            carRepo.updateCarDamagePrice(getSerialFromContractID(contractID),damagePrice);
            if (damageCheck == 1){
                carRepo.updateCarDamaged(getSerialFromContractID(contractID));
            }

            psts.executeUpdate();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSerialFromContractID(int contractID) {
        List<LeasingContract> contracts = new ArrayList<>();

        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing where contractID=?");
            psts.setInt(1, contractID);
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                contracts.add(new LeasingContract(
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type")));
            }
            return contracts.get(0).getSerialnumber();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LeasingContract> getAllContracts() {

        List<LeasingContract> contracts = new ArrayList<>();
        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing");
            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                contracts.add(new LeasingContract(
                        resultSet.getInt("contractID"),
                        resultSet.getString("type"),
                        resultSet.getString("serialnumber"),
                        resultSet.getString("startdate"),
                        resultSet.getString("enddate")
                ));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return contracts;
    }


    public List<LeasingContract> getAllContractsWithAllInfo() {

        List<LeasingContract> contract = new ArrayList<>();
        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing");

            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                contract.add(new LeasingContract(
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
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return contract;
    }

    public List<LeasingContract> getAccountingIncome() {
        int priceMonthly = 0;
        int priceAnnual = 0;
        List<LeasingContract> income = new ArrayList<>();
        try {
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing");

            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                priceMonthly = priceMonthly + resultSet.getInt("priceMonthly");
                priceAnnual = priceAnnual + resultSet.getInt("priceAnnual");
            }

            income.add(new LeasingContract(priceMonthly, priceAnnual));

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return income;
    }

    public List<LeasingContract> getAccountingCars() throws SQLException {
        int numberOfContracts = 0;
        List<LeasingContract> leasedCars = new ArrayList<>();

            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing");

            ResultSet resultSet = psts.executeQuery();
            while (resultSet.next()) {
                numberOfContracts = numberOfContracts + 1;
            }
            leasedCars.add(new LeasingContract(numberOfContracts));

        return leasedCars;
        }

}
