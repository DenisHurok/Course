package com.example.course.repos;

import com.example.course.model.TypeOfPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<TypeOfPlace,Integer> {
    boolean existsByName(String typeofPlace);
    Optional<TypeOfPlace>  findByName(String name); //
}
