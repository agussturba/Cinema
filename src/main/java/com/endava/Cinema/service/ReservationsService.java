package com.endava.Cinema.service;

import com.endava.Cinema.exceptions.*;
import com.endava.Cinema.exceptions.notFoundExceptions.ClientNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.ReservationNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.SeatNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.ShowTimeNotFoundException;
import com.endava.Cinema.model.Reservation;
import com.endava.Cinema.vo.ReservationVo;

import java.util.List;

public interface ReservationsService {
    Reservation saveOrUpdateReservation(ReservationVo reservation) throws ClientNotFoundException, SeatNotFoundException, ShowTimeNotFoundException, ReservationNotFoundException, InvalidSeatReservationException;

    Reservation getReservationById(Integer idReservation) throws ReservationNotFoundException;

    void deleteReservationById(Integer idReservation) throws ReservationNotFoundException;

    List<Reservation> getReservationsByClientId(Integer id) throws ClientNotFoundException;

}
