package com.endava.Cinema.service;

import com.endava.Cinema.exceptions.notFoundExceptions.MovieNotFoundException;
import com.endava.Cinema.model.Movie;
import com.endava.Cinema.vo.MovieVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.endava.Cinema.persistance.MovieRepository;

import java.util.List;

@Service
public class MovieServiceImplementation implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImplementation(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public Page<Movie> getAllMovies(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return  movieRepository.findAll(pageable);
    }

    @Override
    public Movie saveOrUpdateMovie(MovieVo movie) throws MovieNotFoundException {
        if (movie.getId() != null) {
            getMovieById(movie.getId());
        }
        return movieRepository.save(movie.toModel());
    }

    @Override
    public void deleteMovie(Integer idMovie) throws MovieNotFoundException {
        movieRepository.delete(this.getMovieById(idMovie));
    }

    public Movie getMovieById(Integer movieId) throws MovieNotFoundException {
        return movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
    }
}
