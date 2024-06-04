package com.example.course.controller;




import com.example.course.model.Category;
import com.example.course.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;


@Controller
public class MainController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("products", categories);
        model.addAttribute("filter", filter);
        return "main";
    }

}