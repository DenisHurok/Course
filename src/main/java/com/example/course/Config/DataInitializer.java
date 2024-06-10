//package com.example.course.Config;
//
//import com.example.course.model.Genre;
//import com.example.course.model.Role;
//import com.example.course.model.TypeOfPlace;
//import com.example.course.model.User;
//import com.example.course.repos.GenreRepository;
//import com.example.course.repos.TypeRepository;
//import com.example.course.repos.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Collections;
//import java.util.List;
//
//@Component
//public class DataInitializer {
//    private final List<String> genreTypes = List.of("Action", "Adventure", "Comedy", "Drama", "Horror", "Thriller");
//    private final List<String> placeTypes = List.of("Музей", "Цирк", "Кафе", "Библиотека", "Кинотеатр");
//
//    private final GenreRepository genreRepository;
//    private final UserRepo userRepo;
//    private final TypeRepository typeRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public DataInitializer(GenreRepository genreRepository, UserRepo userRepo, TypeRepository typeRepository, PasswordEncoder passwordEncoder) {
//        this.genreRepository = genreRepository;
//        this.userRepo = userRepo;
//        this.typeRepository = typeRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostConstruct
//    public void init() {
//        initializeGenres();
//        initializeUsers();
//        initTypes();
//    }
//
//    private void initializeGenres() {
//        for (String genreType : genreTypes) {
//            // Check if the genre already exists in the database
//            if (!genreRepository.existsByName(genreType)) {
//                // If it doesn't exist, add it
//                Genre genre = new Genre(genreType);
//                genreRepository.save(genre);
//            }
//        }
//    }
//
//    private void initializeUsers() {
//        // Check and create an admin user
//        if (!userRepo.existsByUsername("a")) {
//            User admin = new User();
//            admin.setUsername("a");
//            admin.setActive(true);
//            admin.setPassword(passwordEncoder.encode("a")); // Encrypt the password
//            admin.setRoles(Collections.singleton(Role.ADMIN));
//            userRepo.save(admin);
//        }
//
//        // Check and create a regular user
//        if (!userRepo.existsByUsername("u")) {
//            User user = new User();
//            user.setUsername("u");
//            user.setActive(true);
//            user.setPassword(passwordEncoder.encode("u")); // Encrypt the password
//            user.setRoles(Collections.singleton(Role.USER));
//            userRepo.save(user);
//        }
//    }
//
//    private void initTypes() {
//        for (String type : placeTypes) {
//            // Check if the type already exists in the database
//            if (!typeRepository.existsByName(type)) {
//                // If it doesn't exist, add it
//                TypeOfPlace typeOfPlace = new TypeOfPlace();
//                typeOfPlace.setName(type);
//                typeRepository.save(typeOfPlace);
//            }
//        }
//    }
//}
