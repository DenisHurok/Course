package com.example.course.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
