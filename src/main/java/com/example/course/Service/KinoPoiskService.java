package com.example.course.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class KinoPoiskService {

    @Value("${kinopoisk.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        return new HttpEntity<>(headers);
    }

    public List<Map<String, Object>> getAllMovies() {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.kinopoisk.dev/v1.3/movie")
                .queryParam("limit", 10)
                .toUriString();

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(), Map.class);
        return (List<Map<String, Object>>) response.getBody().get("docs");
    }

    public List<Map<String, Object>> getMovies(String genre, Integer rating) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl("https://api.kinopoisk.dev/v1.3/movie")
                .queryParam("limit", 10);

        if (genre != null && !genre.isEmpty()) {
            urlBuilder.queryParam("genres.name", genre);
        }
        if (rating != null) {
            urlBuilder.queryParam("rating.kp", "1-"+rating);
        }

        String url = urlBuilder.toUriString();
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(), Map.class);
        return (List<Map<String, Object>>) response.getBody().get("docs");
    }

    public Map<String, Object> getMovieDetails(String movieId) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.kinopoisk.dev/v1.3/movie/" + movieId)
                .toUriString();

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(), Map.class);
        return response.getBody();
    }

    public List<String> getGenres() {
        // Здесь вы можете получить доступные жанры из API или использовать статический список
        return Arrays.asList("Action", "Comedy", "Drama", "Fantasy", "Horror", "Romance", "Sci-Fi", "Thriller");
    }
}
