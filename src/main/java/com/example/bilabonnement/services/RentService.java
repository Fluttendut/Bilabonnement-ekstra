package com.example.bilabonnement.services;

import com.example.bilabonnement.models.Accounting;
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
            Accounting accounting = new Accounting();

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

            //addToAccounting(leasingContract);

            psts.executeUpdate();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void cancelRentalContract(int contractID, int damageCheck, String damagePrice) {
        try {
            CarRepository carRepo = new CarRepository();

            PreparedStatement psts2 = conn.prepareStatement("delete from bilabonnement.leasing where contractID=?");
            psts2.setInt(1, contractID);
            carRepo.updateCarAvailable(getSerialFromContractID(contractID));
            carRepo.updateCarDamagePrice(getSerialFromContractID(contractID),damagePrice);
            if (damageCheck == 1){
                carRepo.updateCarDamaged(getSerialFromContractID(contractID));
            }

            psts2.executeUpdate();

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

    public void addToAccounting(LeasingContract leasingContract) throws SQLException {


        /*
        Accounting accounting = new Accounting();

        accounting.setCurrentLeasedCars(accounting.getCurrentLeasedCars() + 1);
        System.out.println(accounting.getCurrentLeasedCars());

        accounting.setMonthlyIncome(accounting.getMonthlyIncome() + leasingContract.getPriceMonthly());
        System.out.println(accounting.getMonthlyIncome());

        accounting.setAnnualIncome(accounting.getAnnualIncome() + (leasingContract.getPriceMonthly()*12));
        System.out.println(accounting.getAnnualIncome());

        accounting.money();
        System.out.println(accounting.money());
        */

    }
    public void removeFromAccounting(LeasingContract leasingContract, Accounting accounting) throws SQLException {
        accounting.setCurrentLeasedCars(accounting.getCurrentLeasedCars() - 1);
        accounting.setMonthlyIncome(accounting.getMonthlyIncome() - leasingContract.getPriceMonthly());
        accounting.setAnnualIncome(accounting.getAnnualIncome() - (leasingContract.getPriceMonthly()*12));
    }

}
