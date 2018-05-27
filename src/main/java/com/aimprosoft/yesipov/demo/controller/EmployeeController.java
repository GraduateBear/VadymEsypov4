package com.aimprosoft.yesipov.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;

@Controller
@RequestMapping("/cont")
public class EmployeeController {

    @Autowired
    private DispatcherServlet dispatcherServlet;

    @GetMapping
    public String foo() {
        System.out.println("asdasd");
        return "index";
    }
}
