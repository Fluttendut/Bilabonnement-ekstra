package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.User;
import com.example.bilabonnement.repositories.CarRepository;
import com.example.bilabonnement.repositories.LogginginRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class HomeController {
    LogginginRepository repo = new LogginginRepository();
    public class htmlController {
        @GetMapping("/")
        public String index() {
            return "index";
        }
}

    @PostMapping("/login")
    public String login(HttpSession session, WebRequest req){

        User user = new User(req.getParameter("user"), req.getParameter("pass"));
        int userId = repo.loggedin(user);

        if(userId == -1){
            return "redirect:/";
        } else {
            session.setAttribute("log", userId);
            return "loggedInAdmin";
        }
    }

}

// Ting vi tror fungerer / skal testes
//TODO create car
//TODO delete car
//TODO update car (damaged)
//TODO update car (available)
//TODO get available car
//TODO get all cars
//TODO get damaged cars
//TODO get rented cars
//TODO get rentee (from name or email)
//TODO create rentee
//TODO delete rentee
//TODO update rentee

// Ting vi mangler
//TODO login
//TODO alt html / css
//TODO test af alt
//TODO binde tingene sammen
//TODO pris ved skade
//TODO sammenlagt pris for udlejede biler
//TODO registrer nye lejeaftaler
