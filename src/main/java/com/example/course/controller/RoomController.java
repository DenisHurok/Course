package com.example.course.controller;


import com.example.course.model.Category;
import com.example.course.model.Room;
import com.example.course.model.User;
import com.example.course.repos.CategoryRepository;


import com.example.course.repos.RoomRepository;
import com.example.course.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    @GetMapping("/main")
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "rooms";
    }

    @GetMapping("/{roomId}")
    public String viewRoom(@PathVariable String roomId, Model model) {
        Room room = roomRepository.findByUuid(roomId);
        if (room == null) {
            return "redirect:/rooms";
        }
        model.addAttribute("room", room);
        model.addAttribute("options", roomRepository.findByUuid(room.getUuid()));
        return "room";
    }

    @PostMapping("/create")
    public String createRoom(@ModelAttribute Room room, @RequestParam Long categoryId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userRepository.findByUsername(username);
            Category category = categoryRepository.findById(categoryId).orElse(null);
            room.setCategory(category);
            room.setUuid(UUID.randomUUID().toString());
            Room savedRoom = roomRepository.save(room);
            return "redirect:/rooms/" + savedRoom.getUuid();
        } else {
            return "redirect:/login";
        }
    }
}
