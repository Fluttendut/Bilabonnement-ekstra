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
    LeasingContract contract = new LeasingContract();

    public HomeController() throws IOException
    {
    }

//    public class htmlController
//    {
//        @GetMapping("/")
//        public String index()
//        {
//            return "index";
//        }
//    }

    //TEMP login så vi kan arbejde med næste side
    @GetMapping("/")
    public String login(Model model)
    {
        model.addAttribute("AllCars", repo.getAllCars());
        model.addAttribute("SmallCars", repo.getAllCarsByType("small"));
        model.addAttribute("MediumCars", repo.getAllCarsByType("medium"));
        model.addAttribute("BigCars", repo.getAllCarsByType("big"));
        model.addAttribute("LuxuryCars", repo.getAllCarsByType("luxury"));

        model.addAttribute("allContracts", rent.getAllContracts());

        model.addAttribute("damagedCars",repo.getAllDamagedCars());

        return "frontpage";
    }



    @PostMapping("/createleasing")
    public String createLeasing(LeasingContract leasingContract, Rentee rentee)
    {

        rent.createRentalContract(leasingContract, rentee);

        return "redirect:/";
    }

    @PostMapping("/cancelleasing")
    public String cancelLeasing(LeasingContract leasingContract, int damageCheck)
    {
        rent.cancelRentalContract(leasingContract.getContractID(), damageCheck);
        //carRepository.updateCarDamage(leasingContract.getSerialnumber()); //todo test this function make html for it
        
        return "redirect:/";
    }

    @PostMapping("/service")
    public String service(LeasingContract leasingContract)
    {
        repo.updateCarDamaged(leasingContract.getSerialnumber());
        return "redirect:/";
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
//TODO update car (damaged)

// Ting vi tror fungerer / skal testes
//TODO create car
//TODO delete car
//TODO binde tingene sammen

// Ting vi mangler
//TODO opdater html design (især index)
//TODO pris ved skade
//TODO sammenlagt pris for udlejede biler (accounting) html
//todo fejlhåndtering ved forkert indput
//todo add pattern to input boxes
//todo mekaniker siden html (service)
//todo accounting siden html
//todo ekstra udstyr? lav som checkbox
//todo clean up code

//ting vi har skåret fra
//TODO login

// TO DO today

