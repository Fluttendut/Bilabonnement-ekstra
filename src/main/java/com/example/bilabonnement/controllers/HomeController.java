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

    //GetMapping is used to handle the GET type of request method.
    @GetMapping("/")
    //We create a String method that parses our model(Class)
    public String startpage(Model model) throws SQLException {
        //We use the model to call a SQL statement for the tables in our database, and assign the returned value to a name
        //repo.getAllCars below is the method where the specific SQL select statement is used for line 32
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


    //PostMapping is used to handle POST type of request method
    @PostMapping("/createleasing")
    public String createLeasing(LeasingContract leasingContract, Rentee rentee)
    {
        //We insert the parameters leasingContract and rentee into a new row in our database table
        rent.createRentalContract(leasingContract, rentee);
        //We redirect to our startpage
        return "redirect:/";
    }

    @PostMapping("/cancelleasing")
    public String cancelLeasing(LeasingContract leasingContract, int damageCheck, String damagePrice)
    {
        //We delete the parameters in leasingContract by ID and update the car leased with a damageCheck and damagePrice method.
        rent.cancelRentalContract(leasingContract.getContractID(), damageCheck, damagePrice);
        
        return "redirect:/";
    }

    @PostMapping("/service")
    public String service(LeasingContract leasingContract)
    {
        //update the car leased with a damageCheck and damagePrice method.
        repo.updateCarDamaged(leasingContract.getSerialnumber());
        repo.resetDamagePrice(leasingContract.getSerialnumber());
        return "redirect:/";
    }

}