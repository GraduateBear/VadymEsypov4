package com.aimprosoft.yesipov.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class WelcomePageController {

    @GetMapping
    public ModelAndView returnWelcomePage(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
