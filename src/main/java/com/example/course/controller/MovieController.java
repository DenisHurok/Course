package com.example.course.controller;

import com.example.course.POJO.PojoMovie;
import com.example.course.Service.CabinetService;
import com.example.course.Service.MovieService;
import com.example.course.Service.UserService;
import com.example.course.model.Genre;
import com.example.course.model.Movie;
import com.example.course.model.User;
import com.example.course.repos.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private static final String POSTERS_DIR = "src/main/resources/static/Posters/";

    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;
    @Autowired
    private CabinetService cabinetService;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/movies")
    public String getAllMovies(@RequestParam(required = false) Integer year,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) Integer genreId,
                               @RequestParam(required = false) Double rating,
                               Model model) {
        List<Movie> movies = movieService.filterMovie(name, rating, genreId, year);
        List<Genre> genres = movieService.getAllGenres();
        model.addAttribute("genres", genres);
        model.addAttribute("movies", movies);
        model.addAttribute("selectedGenre", genreId);
        model.addAttribute("rating", rating);
        return "movies";
    }

    @GetMapping("/addMovie")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "addMovie"; // Return the name of the view template (e.g., addMovie.html)
    }

    @PostMapping("/addMovie")
    public String addMovie(@ModelAttribute PojoMovie moviePojo, @RequestParam List<Integer> genreIds,
                           @RequestParam("poster") MultipartFile posterFile, RedirectAttributes redirectAttributes) {
        try {
            Movie movie = new Movie();
            List<Genre> genres = genreIds.stream()
                    .map(id -> genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not found")))
                    .collect(Collectors.toList());
            movie.setGenres(genres);

            if (!posterFile.isEmpty()) {
                byte[] bytes = posterFile.getBytes();
                Path path = Paths.get(POSTERS_DIR + posterFile.getOriginalFilename());
                Files.createDirectories(path.getParent()); // Create directories if they don't exist
                Files.write(path, bytes);
                movie.setPoster("/Posters/" + posterFile.getOriginalFilename());
            }

            movie.setName(moviePojo.getName());
            movie.setRating(moviePojo.getRating());
            movie.setYear(moviePojo.getYear());
            movie.setLongDescription(moviePojo.getLongDescription());
            movie.setShortDescription(moviePojo.getShortDescription());

            movieService.saveMovie(movie);
            logger.info("Movie successfully added: {}", movie.getName());

            // Redirect to a success page or the movies listing page
            return "redirect:/movies";
        } catch (IOException e) {
            logger.error("Error saving the poster file: ", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Error saving the poster file. Please try again.");
            return "redirect:/addMovie"; // Redirect to the same page to show the error
        } catch (Exception e) {
            logger.error("Error adding the movie: ", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding the movie. Please try again.");
            return "redirect:/addMovie"; // Redirect to the same page to show the error
        }
    }

    @GetMapping("/editMovie")
    public String showEditMovieForm(@RequestParam("id") int id, Model model) {
        Movie movie = movieService.getMovieById(id);
        List<Genre> allGenres = genreRepository.findAll();
        model.addAttribute("movie", movie);
        model.addAttribute("allGenres", allGenres);
        return "editMovie";
    }

    @GetMapping("/movies/{id}")
    public String showInfoAboutMovie(@RequestParam("id") int id, Model model) {
        Movie movie = movieService.getMovieById(id);
        List<Genre> allGenres = genreRepository.findAll();
        model.addAttribute("movie", movie);
        model.addAttribute("allGenres", movie.getGenres());
        return "aboutMovie";
    }

    @PostMapping("/deleteMovie")
    public String deleteMovie(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        try {
            movieService.deleteMovie(id);
            logger.info("Movie successfully deleted with ID: {}", id);
            return "redirect:/movies";
        } catch (Exception e) {
            logger.error("Error deleting the movie with ID: {}", id, e);
            redirectAttributes.addFlashAttribute("error", "An error occurred while deleting the movie.");
            return "redirect:/movies";
        }
    }
    @PostMapping("/SaveMovie")
    public String SaveToCabinet(@RequestParam("idMovie") int idMovie,@RequestParam("idUser") long idUser) {
        Optional<User> optionalUser = userService.findById(idUser);

        if (optionalUser.isPresent()) { // Проверяем, есть ли пользователь в базе данных
            User user = optionalUser.get(); // Получаем объект пользователя из Optional
            Movie movie = movieService.getMovieById(idMovie); // Получаем объект фильма

            cabinetService.saveMovie(movie, user);
        }
        return "redirect:/movies";}
    @PostMapping("/editMovie")
    public String updateMovie(@RequestParam("id") int id, @ModelAttribute Movie movie, @RequestParam List<Integer> genreIds,
                              @RequestParam("poster") MultipartFile posterFile, RedirectAttributes redirectAttributes) {
        try {
            // Find the existing movie by ID
            Movie existingMovie = movieService.getMovieById(id);

            // Update fields
            existingMovie.setName(movie.getName());
            existingMovie.setYear(movie.getYear());
            existingMovie.setRating(movie.getRating());
            existingMovie.setShortDescription(movie.getShortDescription());
            existingMovie.setLongDescription(movie.getLongDescription());

            // Update genres
            List<Genre> genres = genreIds.stream()
                    .map(genreRepository::findById)
                    .map(optionalGenre -> optionalGenre.orElseThrow(() -> new RuntimeException("Genre not found")))
                    .collect(Collectors.toList());
            existingMovie.setGenres(genres);

            // Update poster if a new one is uploaded
            if (!posterFile.isEmpty()) {
                byte[] bytes = posterFile.getBytes();
                Path path = Paths.get(POSTERS_DIR + posterFile.getOriginalFilename());
                Files.createDirectories(path.getParent()); // Create directories if they don't exist
                Files.write(path, bytes);
                existingMovie.setPoster("/Posters/" + posterFile.getOriginalFilename());
            }

            // Save updated movie
            movieService.saveMovie(existingMovie);
            logger.info("Movie successfully updated: {}", existingMovie.getName());
            return "redirect:/movies";
        } catch (IOException e) {
            logger.error("Error saving the poster file: ", e);
            redirectAttributes.addFlashAttribute("error", "Could not save the poster file.");
            return "redirect:/editMovie?id=" + id;
        } catch (Exception e) {
            logger.error("Error updating the movie: ", e);
            redirectAttributes.addFlashAttribute("error", "An error occurred while updating the movie.");
            return "redirect:/editMovie?id=" + id;
        }
    }
}
