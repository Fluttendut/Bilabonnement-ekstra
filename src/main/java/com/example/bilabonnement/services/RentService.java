package com.example.bilabonnement.services;

import com.example.bilabonnement.models.LeasingContract;
import com.example.bilabonnement.models.Rentee;
import com.example.bilabonnement.repositories.CarRepository;
import com.example.bilabonnement.repositories.DatabaseConnectionManager;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentService {
    //Creating an instance of the Connection class.
    private Connection conn = DatabaseConnectionManager.getConnection();

    public RentService() throws IOException {
    }
    //We insert the parameters leasingContract and Rentee into a new row in our database table
    public void createRentalContract(LeasingContract leasingContract, Rentee rentee) {
        //We use Try to run the code to see if the code will run
        try {
            CarRepository carRepository = new CarRepository();
            //We prepare a SQL statement that insert our attributes into our Leasing table
            // The question marks makes our query dynamic rather than static.
            PreparedStatement psts = conn.prepareStatement("insert into bilabonnement.leasing(type, startdate,enddate,serialnumber, priceMonthly, priceTotal,priceAnnual,leasingperiod,name, Email, CPR,address) values(?,?,?,?,?,?,?,?,?,?,?,?)");
            psts.setString(1, leasingContract.getType());
            psts.setString(2, leasingContract.getStartdate());
            psts.setString(3, leasingContract.getEnddate());
            //here we check if the serialnumber is available or not, and return an answer based on the returned results.
            if (leasingContract.getSerialnumber().equals("") || checkIfSerialnumberIsAvailable(leasingContract.getSerialnumber()) == false) {
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
            //We execute the table with the updated values
            psts.executeUpdate();

            //If the try fails, we will catch it here with an exception so that we can see what went wrong
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);

        }
    }


    //We delete the parameters in leasingContract by ID and update the car leased with a damageCheck and damagePrice method.
    public void cancelRentalContract(int contractID, int damageCheck, String damagePrice) {
        try {
            CarRepository carRepo = new CarRepository();

            PreparedStatement psts = conn.prepareStatement("delete from bilabonnement.leasing where contractID=?");
            psts.setInt(1, contractID);
            carRepo.updateCarAvailable(getSerialnumberFromContractID(contractID));
            carRepo.updateCarDamagePrice(getSerialnumberFromContractID(contractID),damagePrice);
            if (damageCheck == 1){
                carRepo.updateCarDamaged(getSerialnumberFromContractID(contractID));
            }

            psts.executeUpdate();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    //in this method we select everthing associated with a specifik contractID
    public String getSerialnumberFromContractID(int contractID) {
        List<LeasingContract> contracts = new ArrayList<>();

        try {
            //here we try to select and call the specific id from the database
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing where contractID=?");
            psts.setInt(1, contractID);
            ResultSet resultSet = psts.executeQuery();

            //here we continue to search for more results and don't stop before we have all instances of this contractID
            while (resultSet.next()) {
                contracts.add(new LeasingContract(
                        resultSet.getString("serialnumber"),
                        resultSet.getString("type")));
            }
            //here we return the first element of our arrayList, as there should never be more than 1 hit on a specific contractID
            return contracts.get(0).getSerialnumber();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    //method to get all contracts
    public List<LeasingContract> getAllContracts() {

        List<LeasingContract> contracts = new ArrayList<>();
        try {
            //here we select all contracts in the database and add them to an arrayList
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing");
            ResultSet resultSet = psts.executeQuery();
            //here we keep asking the system if we have more contacts left in the database that is addable to the list before we present it
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

    //methods that returns all cortracts and all info
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

    //method that returns expected income
    public List<LeasingContract> getAccountingIncome() {
        //here we initiate our variables of out income
        int priceMonthly = 0;
        int priceAnnual = 0;
        List<LeasingContract> income = new ArrayList<>();
        try {
            //we choose everything from the database where from the leasing table
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing");

            ResultSet resultSet = psts.executeQuery();
            //As long as there is a next in our resultSet we update our variables in this method
            while (resultSet.next()) {
                //We here take the old value of our priceMonthly variable and add the new priceMonthly and update the variable with the total
                priceMonthly = priceMonthly + resultSet.getInt("priceMonthly");
                priceAnnual = priceAnnual + resultSet.getInt("priceAnnual");
            }
            //here we add the income together
            income.add(new LeasingContract(priceMonthly, priceAnnual));

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        // returns the arraylist with the priceMonthly and priceAnnual attribute
        return income;
    }

    // this method is used to select all leasing contracts from the database and count them.
    // this could also have been solved by the aggregate function count()
    // here is an example SELECT COUNT(contractID) AS NumberOfContracts FROM bilabonnement.leasing;
    public List<LeasingContract> getAccountingCars() throws SQLException {
        int numberOfContracts = 0;
        List<LeasingContract> leasedCars = new ArrayList<>();
            //select all leasing contracts from the database
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.leasing");

            ResultSet resultSet = psts.executeQuery();
            //if there are more results in database after the first one we loop though the database until we no longer receive a result.
            while (resultSet.next()) {
                //we take the variable value numberOfContracts and update it to be numberOfContracts + 1
                numberOfContracts = numberOfContracts + 1;
            }
            leasedCars.add(new LeasingContract(numberOfContracts));
        //We return the cars we have put into our leasedCars Arraylist
        return leasedCars;
        }

        //
        public boolean checkIfSerialnumberIsAvailable(String serialnumber) throws SQLException {
            boolean checkIfAvailable;
            PreparedStatement psts = conn.prepareStatement("select available from bilabonnement.cars where serialnumber=?");
            psts.setString(1, serialnumber);
            //If the psts is equal 1(available = 1) then it sets the boolean checkIfAvailable to true
            //Available is a binary so that we only get it to be true if it is 1, everything else is false
            if (psts.equals(1)){
                checkIfAvailable = true;
            } else checkIfAvailable = false;

            return checkIfAvailable;
        }

}
