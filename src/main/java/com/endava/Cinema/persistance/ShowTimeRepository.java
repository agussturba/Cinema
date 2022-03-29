package com.endava.Cinema.persistance;

import com.endava.Cinema.model.Movie;
import com.endava.Cinema.model.ShowTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowTimeRepository extends CrudRepository<ShowTime, Integer> {
    List<ShowTime> findByMovie(Movie movie);

    List<ShowTime> findByDayAndRoom(LocalDate day, Integer roomId);

}
