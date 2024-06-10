package com.example.course.repos;

import com.example.course.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinetMovieRepository extends JpaRepository<Movie, Integer> {

}
