package com.example.course.repos;

import com.example.course.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findById(Integer id);
    Integer findByName(String name);

    boolean existsByName(String genreType);
}
