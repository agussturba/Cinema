package com.endava.Cinema.service;

import com.endava.Cinema.exceptions.*;
import com.endava.Cinema.exceptions.notFoundExceptions.ClientNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.ReservationNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.SeatNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.ShowTimeNotFoundException;
import com.endava.Cinema.model.User;
import com.endava.Cinema.model.Reservation;
import com.endava.Cinema.model.Seat;
import com.endava.Cinema.model.ShowTime;
import com.endava.Cinema.persistance.SeatRepository;
import com.endava.Cinema.persistance.ShowTimeRepository;
import com.endava.Cinema.vo.ReservationVo;
import org.springframework.stereotype.Service;
import com.endava.Cinema.persistance.UserRepository;
import com.endava.Cinema.persistance.ReservationRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReservationServiceImplementation implements ReservationsService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final ShowTimeRepository showTimeRepository;

    public ReservationServiceImplementation(ReservationRepository reservationRepository, UserRepository userRepository, SeatRepository seatRepository, ShowTimeRepository showTimeRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.seatRepository = seatRepository;
        this.showTimeRepository = showTimeRepository;
    }

    @Override
    public Reservation saveOrUpdateReservation(ReservationVo reservation) throws ClientNotFoundException, SeatNotFoundException, ShowTimeNotFoundException, ReservationNotFoundException, InvalidSeatReservationException {
        if (reservation.getId() != null) {
            reservationRepository.findById(reservation.getId()).orElseThrow(ReservationNotFoundException::new);
        }
        User user = userRepository.findById(reservation.getUserId()).orElseThrow(ClientNotFoundException::new);

        List<String> listOfSeatsIds = reservation.getSeatsList();

        List<Seat> seatList = getSeatsByIds(listOfSeatsIds);

        ShowTime showTime = showTimeRepository.findById(reservation.getShowTimeId()).orElseThrow(ShowTimeNotFoundException::new);

        validateSeats(showTime, seatList);
        Reservation newReservation = new Reservation(seatList, showTime, user);
        return reservationRepository.save(newReservation);
    }

    @Override
    public Reservation getReservationById(Integer idReservation) throws ReservationNotFoundException {
        return reservationRepository.findById(idReservation).orElseThrow(ReservationNotFoundException::new);
    }

    @Override
    public void deleteReservationById(Integer idReservation) throws ReservationNotFoundException {
        Reservation reservation = this.getReservationById(idReservation);
        reservationRepository.delete(reservation);
    }

    @Override
    public List<Reservation> getReservationsByClientId(Integer id) throws ClientNotFoundException {
        User user = userRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return reservationRepository.findByUser(user);
    }

    public List<Seat> getSeatsByIds(List<String> seatsList) throws SeatNotFoundException {
        if (seatsList == null) {
            throw new NoSeatsException();
        }
        List<Seat> seats = new ArrayList<>();
        for (String seatId : seatsList) {
            Seat seat = seatRepository.findById(seatId).orElseThrow(SeatNotFoundException::new);
            seats.add(seat);
        }
        return seats;
    }


    private void validateSeats(ShowTime showTime, List<Seat> seatList) throws InvalidSeatReservationException {
        List<Seat> unAvailableSeats = getUnAvailableSeats(showTime);
        for (Seat seat : seatList) {
            if (unAvailableSeats.indexOf(seat) != -1) {
                throw new InvalidSeatReservationException();
            }
        }
    }

    private List<Seat> getUnAvailableSeats(ShowTime showTime) {
        List<Reservation> reservations = reservationRepository.findByShowTime(showTime);
        List<Seat> seatList = new ArrayList<>();
        List<Seat> unavailableSeats = new ArrayList<>();
        for (Reservation reservation : reservations) {//all seats reserved
            for (Seat seat: reservation.getSeatList()) {
                seatList.add(seat);
            }
        }
        if (seatList.size() > 0) {
            for (Seat seat : seatList) {//Seats that cant be reserved because of regulation

                if (seatList.indexOf(seat.getPreviousSeat()) != -1) {
                    unavailableSeats.add(seat.getPreviousSeat());

                }
            }
        }
        unavailableSeats.addAll(seatList);
        return unavailableSeats;
    }

}
