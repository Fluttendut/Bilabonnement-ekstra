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

    public List<Rentee> getCostumer(String name) {

        List<Rentee> rentee = new ArrayList<>();
        try {
            // spørgsmålstegnet gør vores query dynamisk i stedet for statisk
            PreparedStatement psts = conn.prepareStatement("select * from bilabonnement.rentee WHERE name =? or email =?");
            psts.setString(1, "name");
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
}
