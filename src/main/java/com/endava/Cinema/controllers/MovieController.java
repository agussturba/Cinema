package com.endava.Cinema.controllers;

import com.endava.Cinema.exceptions.notFoundExceptions.MovieNotFoundException;
import com.endava.Cinema.model.Movie;
import com.endava.Cinema.service.MovieService;
import com.endava.Cinema.vo.MovieVo;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/genre/{genre}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")//OK
    public ResponseEntity<List<Movie>> getAllMoviesByGenre(@PathVariable String genre) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMoviesByGenre(genre));
    }

    @GetMapping("/{title}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")//OK
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovieByTitle(title));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")//OK
    public ResponseEntity<Page<Movie>> getAllMovies(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies(page, size));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")//OK
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieVo movie) throws MovieNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.saveOrUpdateMovie(movie));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")//OK
    public ResponseEntity<Movie> updateMovie(@RequestBody MovieVo movie) throws MovieNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.saveOrUpdateMovie(movie));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void  deleteMovieById(@PathVariable Integer movieId) throws MovieNotFoundException {
        movieService.deleteMovie(movieId);
    }

}
