package com.example.course.repos;

import com.example.course.Models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepos extends CrudRepository<Product,Long> {
    List<Product> findByNameStartingWith (String name);


}

