package com.example.course.repos;

import com.example.course.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findById(int id);
    List<Movie> findByNameContaining(String name);
    List<Movie> findByGenres_Name(String genre);
    List<Movie> findByRating(String rating);
    List<Movie> findByYear(int year);
    List<Movie> findByGenres_Id(int id);
    void deleteById(int id);
}
