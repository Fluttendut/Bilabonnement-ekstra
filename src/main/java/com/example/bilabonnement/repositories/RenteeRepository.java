package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.Rentee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RenteeRepository {

    private Connection conn = DatabaseConnectionManager.getConnection();

    public RenteeRepository() throws IOException {
    }
/*
    public List<Rentee> getRentee(String name, String email) {

        List<Rentee> rentee = new ArrayList<>();
        try {
            // spørgsmålstegnet gør vores query dynamisk i stedet for statisk
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.rentee WHERE name =? or email =?");
            psts.setString(1, "name");
            psts.setString(2, "email");
            ResultSet resultSet = psts.executeQuery();

            if (resultSet.next()) {
                rentee.add(new Rentee(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("cpr"),
                        resultSet.getString("address")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rentee;
    }

    public List<Rentee> getAllRentees() {

        List<Rentee> rentee = new ArrayList<>();
        try {
            // spørgsmålstegnet gør vores query dynamisk i stedet for statisk
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.rentee");

            ResultSet resultSet = psts.executeQuery();

            if (resultSet.next()) {
                rentee.add(new Rentee(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("cpr"),
                        resultSet.getString("address")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rentee;
    }

    public void CreateRentee(Rentee rentee) {

        try {
            // spørgsmålstegnet gør vores query dynamisk i stedet for statisk
            PreparedStatement psts = conn.prepareStatement("insert into bilabonnement.rentee(name, email, cpr, address) values(?,?,?,?)");
            psts.setString(1, rentee.getName());
            psts.setString(2, rentee.getEmail());
            psts.setString(3, rentee.getCpr());
            psts.setString(4, rentee.getAddress());

            psts.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void deleteRentee(int cpr) {
        try
        {
            PreparedStatement psts = conn.prepareStatement("delete from bilabonnement.rentee where cpr=? ");
            psts.setInt(1,cpr);
            psts.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void updateRentee(int cpr) throws RuntimeException
    {
        try
        {
            PreparedStatement psts = conn.prepareStatement("update bilabonnement.rentee set name=? and email=? and address=? where cpr=?");
            psts.setString(1,"name");
            psts.setString(1,"email");
            psts.setString(1,"address");
            psts.setInt(4,cpr);

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

 */
}
