package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.User;
import com.example.bilabonnement.repositories.CarRepository;
import com.example.bilabonnement.repositories.LogginginRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeController {

    LogginginRepository repo = new LogginginRepository();
    private CarRepository carRepo = new CarRepository();

    public HomeController() throws IOException {
    }

    public class htmlController {
        @GetMapping("/")
        public String index() {
            return "index";
        }


}
}
