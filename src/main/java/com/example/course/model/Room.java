package com.example.course.model;


import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid = UUID.randomUUID().toString();


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
