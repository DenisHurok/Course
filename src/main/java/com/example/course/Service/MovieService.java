package com.example.course.Service;

import com.example.course.model.Genre;
import com.example.course.model.Movie;
import com.example.course.repos.GenreRepository;
import com.example.course.repos.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getMoviesByName(String name) {
        return movieRepository.findByNameContaining(name);
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenres_Name(genre);
    }

    public List<Movie> getMoviesByRating(String rating) {
        return movieRepository.findByRating(rating);
    }

    public List<Movie> getMoviesByYear(int year) {
        return movieRepository.findByYear(year);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie getMovieById(int id) {
        return movieRepository.findById(id);
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> filterMovie(String name, Double rating, Integer genreId, Integer year) {
        System.out.println(name+rating+genreId+year+genreId+year);
        List<Movie> allMovies = movieRepository.findAll();
        for (Movie movie:allMovies.stream()
                .filter(movie -> (name == null || movie.getName().contains(name)) &&
                        (rating == null || Math.abs(Double.parseDouble(movie.getRating()) - rating) < 0.5) &&
                        (year == null || Integer.parseInt(movie.getYear()) == year) &&
                        (genreId == null || movie.getGenres().stream().anyMatch(genre -> genre.getId()==(genreId))))
                .collect(Collectors.toList())
             ) {
            System.out.println(movie.toString());
        }
        return allMovies.stream()
                .filter(movie -> (name == null || movie.getName().contains(name)) &&
                        (rating == null || Math.abs(Double.parseDouble(movie.getRating()) - rating) < 0.5) &&
                        (year == null || Integer.parseInt(movie.getYear()) == year) &&
                        (genreId == null || movie.getGenres().stream().anyMatch(genre -> genre.getId()==(genreId))))
                .collect(Collectors.toList());
    }

}
