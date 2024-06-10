package com.example.course.Service;

import com.example.course.model.CabinetMovies;
import com.example.course.model.Movie;
import com.example.course.model.Place;
import com.example.course.model.User;
import com.example.course.repos.CabinetMovieRepository;
import com.example.course.repos.CabinetPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CabinetService {
    @Autowired
    private CabinetMovieRepository cabinetMovieRepository;
    @Autowired
    private CabinetPlaceRepository cabinetPlaceRepository;

    public List<Movie> getAllMovies() {
        return cabinetMovieRepository.findAll();
    }
    public List<Place> getAllPlaces() {
        return cabinetPlaceRepository.findAll();
    }
    public void saveMovie(Movie movie, User user) {
        CabinetMovies cabinetMovies = new CabinetMovies();
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        cabinetMovies.setMovies(movies);
        cabinetMovies.setUser(user);
    }
    public void savePlace(Place place) {
        cabinetPlaceRepository.save(place);
    }
    public void deleteMovie(Integer id) {
        cabinetMovieRepository.deleteById(id);
    }
    public void deletePlace(Integer id) {
        cabinetPlaceRepository.deleteById(id);
    }
}
