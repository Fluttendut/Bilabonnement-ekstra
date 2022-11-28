package com.example.bilabonnement.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    public class htmlController {
        @GetMapping("/")
        public String index() {
            return "index";
        }
    }
}
