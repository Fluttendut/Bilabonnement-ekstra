package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.User;
import com.example.bilabonnement.repositories.CarRepository;
import com.example.bilabonnement.repositories.LogginginRepository;
import com.example.bilabonnement.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoggingInController
{
    @Controller
    public class LoginController {

//        LogginginRepository repo = new LogginginRepository();
//
//
//
//       @PostMapping("/login")
//        public String login(HttpSession session, WebRequest req){
//
//            User user = new User(req.getParameter("user"), req.getParameter("pass"));
//            int userId = repo.loggedin(user);
//
//            if(userId == -1){
//                return "redirect:/";
//            } else {
//                session.setAttribute("log", userId);
//                return "loggedInAdmin";
//            }
//        }


//        @GetMapping("logout")
//        public String logout(HttpSession session){
//            System.out.println(session.getAttribute("log"));
//            session.invalidate();
//            return "redirect:/";
//        }




    }

}
