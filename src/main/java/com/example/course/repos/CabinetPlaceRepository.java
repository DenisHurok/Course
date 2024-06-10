package com.example.course.repos;

import com.example.course.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinetPlaceRepository extends JpaRepository<Place, Integer> {
}
