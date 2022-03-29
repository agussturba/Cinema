package com.endava.Cinema.exceptions;

import com.endava.Cinema.exceptions.notFoundExceptions.*;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidDateException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidHourException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidRoomException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidScheduleException;
import com.endava.Cinema.exceptions.userException.EmailExistsException;
import com.endava.Cinema.exceptions.userException.InvalidPasswordException;
import com.endava.Cinema.exceptions.userException.InvalidRoleException;
import com.endava.Cinema.exceptions.userException.UserNameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity handleClientNotFoundException(ClientNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client was not found");
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity handleInvalidDateException(InvalidDateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The date cant be before the today date");
    }

    @ExceptionHandler(InvalidHourException.class)
    public ResponseEntity handleInvalidHourException(InvalidHourException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The hours must be in a correct period");
    }

    @ExceptionHandler(InvalidRoomException.class)
    public ResponseEntity handleInvalidDateException(InvalidRoomException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The room id doesn't exist");
    }

    @ExceptionHandler(InvalidScheduleException.class)
    public ResponseEntity handleInvalidScheduleException(InvalidScheduleException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is already a movie playing at that time");
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity handleMovieNotFoundException(MovieNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The movie doesn't exist ");
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity handleReservationNotFoundException(ReservationNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Reservation doesn't exist ");
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity handleSeatNotFoundException(SeatNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Seat doesn't exist ");
    }

    @ExceptionHandler(ShowTimeNotFoundException.class)
    public ResponseEntity handleShowTimeNotFoundException(ShowTimeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The ShowTime doesn't exist ");
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity handleEmailExistsException(EmailExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The email already exists");
    }

    @ExceptionHandler(UserNameExistsException.class)
    public ResponseEntity handleUserNameExistsException(UserNameExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The username already exists");
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity handleInvalidRoleException(InvalidRoleException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The role is not valid, use ADMIN or CLIENT");
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity handleInvalidPasswordException(InvalidPasswordException e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The password is not valid");
    }
    @ExceptionHandler(NoSeatsException.class)
    public ResponseEntity handleNoSeatsException(NoSeatsException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The seats of the reservation cant be null");
    }

    @ExceptionHandler(InvalidSeatReservationException.class)
    public ResponseEntity handleNoSeatsException(InvalidSeatReservationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The seat/s cant be reserve due to politics of the cinema" +
                "or that the seat is already reserved");
    }

}
