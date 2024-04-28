package com.example.course.controller;


import com.example.course.Models.Product;

import com.example.course.repos.ProductRepos;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;


@Controller
public class MainController {
    @Autowired
    private ProductRepos productRepos;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Product> products = productRepos.findAll();
        if (filter != null && !filter.isEmpty()) {
            products = productRepos.findByNameStartingWith(filter);
        } else {
            products = productRepos.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("filter", filter);
        return "main";
    }

}