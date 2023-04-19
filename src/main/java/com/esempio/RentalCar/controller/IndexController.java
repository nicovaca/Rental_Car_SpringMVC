package com.esempio.RentalCar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    //valore di default dell'index
    @RequestMapping()
    public String getWelcome(Model model) {
        return "index";
    }

    @RequestMapping(value = "/index")
    public String getWelcome2(Model model) {
        return "index";
    }

    @RequestMapping("/Homepage")
    public String getWelcome3(Model model) {
        return "index";
    }

}
