package com.example.course.controller;

import com.example.course.Service.KinoPoiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    @Autowired
    private KinoPoiskService kinoPoiskService;

    @GetMapping("/movies")
    public String listMovies(Model model, @RequestParam(required = false) String genre,
                             @RequestParam(required = false) Integer rating) {
        List<Map<String, Object>> movies;
        if (genre != null || rating != null) {
            movies = kinoPoiskService.getMovies(genre, rating);
        } else {
            movies = kinoPoiskService.getAllMovies();
        }
        model.addAttribute("movies", movies);
        model.addAttribute("genres", kinoPoiskService.getGenres());
        model.addAttribute("selectedGenre", genre != null ? genre : "");
        model.addAttribute("rating", rating != null ? rating : "");
        return "movies";
    }

    @GetMapping("/movies/{movieId}")
    public String viewMovieDetails(@PathVariable String movieId, Model model) {
        Map<String, Object> movieDetails = kinoPoiskService.getMovieDetails(movieId);
        model.addAttribute("movieDetails", movieDetails);
        return "movie-details";
    }
}
