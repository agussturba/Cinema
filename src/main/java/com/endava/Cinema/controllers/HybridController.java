package com.endava.Cinema.controllers;

import com.endava.Cinema.exceptions.notFoundExceptions.ClientNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.MovieNotFoundException;
import com.endava.Cinema.model.Reservation;
import com.endava.Cinema.model.ShowTime;
import com.endava.Cinema.service.ReservationsService;
import com.endava.Cinema.service.ShowTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HybridController {
    private final ShowTimeService showTimeService;
    private final ReservationsService reservationsService;

    public HybridController(ShowTimeService showTimeService, ReservationsService reservationsService) {
        this.showTimeService = showTimeService;
        this.reservationsService = reservationsService;
    }

    @GetMapping("/api/{movieId}/showtimes")
    public ResponseEntity<List<ShowTime>> getShowTimesByMovieId(@PathVariable Integer movieId) throws MovieNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(showTimeService.getShowTimesByMovieId(movieId));
    }

    @GetMapping("/api/user/{userId}/reservation")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable Integer userId) throws MovieNotFoundException, ClientNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(reservationsService.getReservationsByClientId(userId));
    }
}
