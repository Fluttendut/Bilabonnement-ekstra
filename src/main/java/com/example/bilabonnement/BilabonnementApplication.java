package com.example.bilabonnement;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.repositories.CarRepository;
import com.example.bilabonnement.services.RentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BilabonnementApplication {

    public static void main(String[] args) {

        SpringApplication.run(BilabonnementApplication.class, args);
    }

}
