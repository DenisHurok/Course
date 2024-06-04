package com.example.course.model;

import java.util.List;

public class Movie {
    private int id;
    private String name;
    private int year;
    private String rating;
    private Poster poster;
    private List<Genre> genres;

    public static class Poster {
        private String url;
        // Getters and setters
    }

    public static class Genre {
        private String name;
        // Getters and setters
    }
}
