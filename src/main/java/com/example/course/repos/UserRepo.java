package com.example.course.repos;

import com.example.course.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
   User findByUsername(String username);

}
