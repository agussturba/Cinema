package com.endava.Cinema.service;

import com.endava.Cinema.exceptions.notFoundExceptions.MovieNotFoundException;
import com.endava.Cinema.model.Movie;
import com.endava.Cinema.vo.MovieVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    List<Movie> getMoviesByGenre(String genre);

    Movie getMovieByTitle(String title);

    Page<Movie> getAllMovies(Integer pageNumber, Integer pageSize);

    Movie saveOrUpdateMovie(MovieVo movie) throws MovieNotFoundException;


    void deleteMovie(Integer idMovie) throws MovieNotFoundException;

}
