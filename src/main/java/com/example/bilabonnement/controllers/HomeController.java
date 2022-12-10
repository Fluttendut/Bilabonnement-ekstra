package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.LeasingContract;
import com.example.bilabonnement.models.Rentee;
import com.example.bilabonnement.repositories.CarRepository;
import com.example.bilabonnement.services.RentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.IOException;


@Controller
public class HomeController
{

    CarRepository repo = new CarRepository();
    RentService rent = new RentService();

    public HomeController() throws IOException
    {
    }

    public class htmlController
    {
        @GetMapping("/")
        public String index()
        {
            return "index";
        }
    }

    //TEMP login så vi kan arbejde med næste side
    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("AllCars", repo.getAllCars());
        model.addAttribute("SmallCars", repo.getAllCarsByType("small"));
        model.addAttribute("MediumCars", repo.getAllCarsByType("medium"));
        model.addAttribute("BigCars", repo.getAllCarsByType("big"));
        model.addAttribute("LuxuryCars", repo.getAllCarsByType("luxury"));

        model.addAttribute("allContracts", rent.getAllContracts());

        return "loggedInAdmin";
    }



    @PostMapping("/createleasing")
    public String createLeasing(LeasingContract leasingContract, Rentee rentee)
    {

        rent.createRentalContract(leasingContract, rentee);

        return "redirect:/login";
    }

    @PostMapping("/cancelleasing")
    public String cancelLeasing(LeasingContract leasingContract, Rentee rentee)
    {
        rent.cancelRentalContract(leasingContract.getContractID());
        //carRepository.updateCarDamage(leasingContract.getSerialnumber()); //todo test this function make html for it
        
        return "redirect:/login";
    }

    @PostMapping("/service")
    public String service()
    {
        return "service";
    }

}

// ting der virker 100%
//TODO update car (available)
//TODO get available car
//TODO get all cars
//TODO get damaged cars
//TODO get rented cars
//TODO get rentee (from name or email)
//TODO opret rental table i DB
//TODO registrer nye lejeaftaler
//TODO delete contract
//TODO sammenlagt pris for udlejede biler (accounting)

// Ting vi tror fungerer / skal testes
//TODO create car
//TODO delete car
//TODO update car (damaged)
//TODO binde tingene sammen

// Ting vi mangler
//TODO pris ved skade
//TODO sammenlagt pris for udlejede biler (accounting) html
//todo fejlhåndtering ved forkert indput
//todo mekaniker siden html (service)
//todo accounting siden html
//todo ekstra udstyr? lav som checkbox


//ting vi har skåret fra
//TODO login

// TO DO today

