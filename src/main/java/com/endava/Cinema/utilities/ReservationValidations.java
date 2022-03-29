package com.endava.Cinema.utilities;

import com.endava.Cinema.exceptions.InvalidSeatReservationException;
import com.endava.Cinema.model.Reservation;
import com.endava.Cinema.model.Seat;
import com.endava.Cinema.model.ShowTime;
import com.endava.Cinema.persistance.ReservationRepository;
import com.endava.Cinema.persistance.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
public class ReservationValidations {



    /*public static void validateSeats(ShowTime showTime, List<Seat> seatList) throws InvalidSeatReservationException {
        List<Seat> unAvailableSeats = getUnAvailableSeats(showTime);
        for (Seat seat : seatList) {
            if (unAvailableSeats.indexOf(seat) != -1) {
                throw new InvalidSeatReservationException();
            }
        }
    }*/

  /*  private static List<Seat> getUnAvailableSeats(ShowTime showTime) {
        List<Reservation> reservations = reservationRepository.findByShowTime(showTime);
        List<Seat> seatList = new ArrayList<>();
        List<Seat> unavailableSeats = new ArrayList<>();
        for (Reservation reservation : reservations) {
            seatList.addAll(reservation.getSeatList());
        }
        for (Seat seat : seatList) {//Seats that cant be reserved because of regulation
            if (seat != null) {
                if (seatList.indexOf(seat.getPreviousSeat()) == -1) {
                    unavailableSeats.add(seat.getPreviousSeat());
                }
            }
        }
        unavailableSeats.addAll(seatList);
        return unavailableSeats;
    }*/

}
