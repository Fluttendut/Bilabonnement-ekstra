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
import java.sql.SQLException;


@Controller
public class HomeController
{

    CarRepository repo = new CarRepository();
    RentService rent = new RentService();

    public HomeController() throws IOException{
    }


    @GetMapping("/")
    public String startpage(Model model) throws SQLException {
        model.addAttribute("AllCars",repo.getAllCars());
        model.addAttribute("SmallCars",repo.getAllCarsByType("small"));
        model.addAttribute("MediumCars",repo.getAllCarsByType("medium"));
        model.addAttribute("BigCars",repo.getAllCarsByType("big"));
        model.addAttribute("LuxuryCars",repo.getAllCarsByType("luxury"));

        model.addAttribute("allContracts",rent.getAllContracts());
        model.addAttribute("ContractsInfo",rent.getAllContractsWithAllInfo());

        model.addAttribute("damagedCars",repo.getAllDamagedCars());

        model.addAttribute("income",rent.getAccountingIncome());
        model.addAttribute("leasedCars",rent.getAccountingCars());

        //Returns the html page "frontpage"
        return "frontpage";
    }



    @PostMapping("/createleasing")
    public String createLeasing(LeasingContract leasingContract, Rentee rentee)
    {
        rent.createRentalContract(leasingContract, rentee);

        return "redirect:/";
    }

    @PostMapping("/cancelleasing")
    public String cancelLeasing(LeasingContract leasingContract, int damageCheck, String damagePrice)
    {
        rent.cancelRentalContract(leasingContract.getContractID(), damageCheck, damagePrice);
        
        return "redirect:/";
    }

    @PostMapping("/service")
    public String service(LeasingContract leasingContract)
    {
        repo.updateCarDamaged(leasingContract.getSerialnumber());
        repo.resetDamagePrice(leasingContract.getSerialnumber());
        return "redirect:/";
    }

}