package com.endava.Cinema.persistance;

import com.endava.Cinema.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer> {
    List<Movie> findByGenre(String genre);
    Movie findByTitle(String title);
    Page<Movie> findAll(Pageable pageable);

}
