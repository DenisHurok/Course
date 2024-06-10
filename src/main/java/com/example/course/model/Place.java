package com.example.course.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String rating;
    @Column(length = 2000)
    private String mapUrl;

    @ManyToMany
    @JoinTable(
            name = "place_type",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private List<TypeOfPlace> types;

    @Transient
    private List<Integer> typeIds = new ArrayList<>();
}
