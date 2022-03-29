package com.endava.Cinema.controllers;

import com.endava.Cinema.exceptions.InvalidSeatReservationException;
import com.endava.Cinema.exceptions.notFoundExceptions.ClientNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.ReservationNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.SeatNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.ShowTimeNotFoundException;
import com.endava.Cinema.model.Reservation;
import com.endava.Cinema.service.ReservationsService;
import com.endava.Cinema.vo.ReservationVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ReservationsService reservationsService;

    public ReservationController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Reservation> saveReservation(@RequestBody ReservationVo reservation) throws ClientNotFoundException, ShowTimeNotFoundException, SeatNotFoundException, ReservationNotFoundException, InvalidSeatReservationException {
        return ResponseEntity.status(HttpStatus.OK).body(reservationsService.saveOrUpdateReservation(reservation));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('reservation:write')")
    public ResponseEntity<Reservation> updateReservation(@RequestBody ReservationVo reservation) throws ReservationNotFoundException, ClientNotFoundException, ShowTimeNotFoundException, SeatNotFoundException, InvalidSeatReservationException {
        return ResponseEntity.status(HttpStatus.OK).body(reservationsService.saveOrUpdateReservation(reservation));
    }

    @GetMapping("/{reservationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<Reservation> getReservationsById(@PathVariable Integer reservationId) throws ReservationNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(reservationsService.getReservationById(reservationId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('reservation:write')")
    public void deleteReservation(@PathVariable Integer reservationId) throws ReservationNotFoundException {
        reservationsService.deleteReservationById(reservationId);
    }

}
