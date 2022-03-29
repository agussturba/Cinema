package com.endava.Cinema.service;

import com.endava.Cinema.exceptions.notFoundExceptions.MovieNotFoundException;
import com.endava.Cinema.model.Movie;
import com.endava.Cinema.persistance.MovieRepository;
import com.endava.Cinema.vo.MovieVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MovieServiceImplementationTest {
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieServiceImplementation movieService;
    private Movie testMovie1;
    private Movie testMovie2;
    private MovieVo testMovieVo;
    private List<Movie> movieList;


    @BeforeEach
    void setUp() {
        movieList = new ArrayList<>();
        testMovie1 = new Movie("Star Wars", "Science-Ficcion", "2h", "Mark Hamill", "George Lucas", "In a Far Far away galaxy", true);
        testMovie2 = new Movie("Star Wars 2", "Science-Ficcion", "2h", "Mark Hamill", "George Lucas", "In another Far Far away galaxy", true);
        testMovieVo = new MovieVo(testMovie1);
        movieList.add(testMovie1);
        movieList.add(testMovie2);
    }

    @Test
    void getMoviesByGenre() {
        when(movieRepository.findByGenre(any(String.class))).thenReturn(movieList);
        List<Movie> movieList = movieService.getMoviesByGenre("Science-Ficcion");
        assertEquals(2, movieList.size());
        assertEquals("Star Wars", movieList.get(0).getTitle());
    }
    @Test
    void getMoviesByGenreNotFoundTest() {
        when(movieRepository.findByGenre(any(String.class))).thenThrow(MovieNotFoundException.class);
        assertThrows(MovieNotFoundException.class, (Executable) movieService.getMoviesByGenre("Science-Ficcion"));
    }

    @Test
    void getMovieByTitle() {
        when(movieRepository.findByTitle(any(String.class))).thenReturn(testMovie1);
        Movie movie = movieService.getMovieByTitle("Star Wars");
        assertNotNull(movie);
        assertEquals("Star Wars", movie.getTitle());
    }

    @Test
    void getAllMovies() {
        when(movieRepository.findAll(any(Pageable.class))).thenReturn((Page<Movie>) movieList);
        Page<Movie> movies = movieService.getAllMovies(1, 2);
        assertNotNull(movies);
        assertEquals(2L, movies.getTotalElements());
        assertEquals((Page<Movie>) movieList, movies);
    }

    @Test
    void saveOrUpdateMovie() throws MovieNotFoundException {
        when(movieRepository.save(any(Movie.class))).thenReturn(testMovie1);
        Movie movie = movieService.saveOrUpdateMovie(testMovieVo);
        assertNotNull(movie);
        assertEquals("Star Wars", movie.getTitle());
    }

    @Test
    void deleteMovie() {
    }
}