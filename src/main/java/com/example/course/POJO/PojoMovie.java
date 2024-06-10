package com.example.course.POJO;

import com.example.course.model.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PojoMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String year;
    private String rating;
    private String shortDescription;
    private String longDescription;
    private List<Genre> genres;

}
