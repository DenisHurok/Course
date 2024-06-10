package com.example.course.POJO;

import com.example.course.model.TypeOfPlace;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PojoPlaces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String imageUrl;
    private Double rating;
    private String mapUrl;

    private List<TypeOfPlace> typePlaces ;

}
