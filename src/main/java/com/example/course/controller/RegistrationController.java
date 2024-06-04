package com.example.course.controller;

import com.example.course.model.Role;
import com.example.course.model.User;
import com.example.course.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userService.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);

        // Assign multiple roles to the user
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        // Add other roles as needed, for example:
        // roles.add(Role.ADMIN);

        user.setRoles(roles);
        userService.saveUser(user);

        return "redirect:/login";
    }
}
