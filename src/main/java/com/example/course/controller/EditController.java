package com.example.course.controller;

import com.example.course.Models.Product;
import com.example.course.Models.Role;
import com.example.course.Models.User;
import com.example.course.repos.ProductRepos;
import com.example.course.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class EditController {
    @Autowired
    private ProductRepos productRepos;

    @GetMapping("/editProduct")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Map<String, Object> model) {
        Iterable<Product> products = productRepos.findAll();
        if (filter != null && !filter.isEmpty()) {
            products = productRepos.findByNameStartingWith(filter);
        } else {
            products = productRepos.findAll();
        }
        model.put("products", products);
        model.put("filter",filter);
        return "editProduct";
    }

    @PostMapping("add")
    public String add(@RequestParam Integer amount, @RequestParam String name, Map<String, Object> model) {
        Product product = new Product(amount, name);
        productRepos.save(product);
        Iterable<Product> products = productRepos.findAll();
        model.put("products", products);
        return "redirect:/editProduct";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Map<String, Object> model) {
        Optional<Product> product = productRepos.findById(id);
        productRepos.delete(product.get());
        Iterable<Product> products = productRepos.findAll();
        model.put("products", products);
        return "redirect:/editProduct";
    }

    @GetMapping("/product/{product}")
    public String productEditForm(@PathVariable Product product, Model model) {
        model.addAttribute("product", product);

        return "editSpecificProd";
    }
    @PostMapping("/product")
    public String save (@RequestParam ("id") Product product,
                       @RequestParam String name,
                       @RequestParam Integer amount,
                       Map<String, Object> model) {

       product.setName(name);
        product.setAmount(amount);
        productRepos.save(product);
        Iterable<Product> products = productRepos.findAll();
        model.put("products", products);
        return "redirect:/editProduct";
    }
}



