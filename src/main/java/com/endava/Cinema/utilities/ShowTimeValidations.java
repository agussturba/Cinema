package com.endava.Cinema.utilities;

import com.endava.Cinema.exceptions.showTimeExceptions.InvalidDateException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidHourException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidRoomException;
import com.endava.Cinema.vo.ShowTimeVo;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowTimeValidations {

    private static void validDay(LocalDate date) throws InvalidDateException {
        if (date.isBefore(LocalDate.now())) {
            throw new InvalidDateException();
        }
    }

    private static void validRoomNumber(Integer roomId) throws InvalidRoomException {
        if (roomId < 1 || roomId > 5) {
            throw new InvalidRoomException();
        }
    }

    private static void validStartingHour(LocalTime time) throws InvalidHourException {//Ask about this
        if (time.getHour() < 8 || time.getHour() > 24) {
            throw new InvalidHourException();
        }
    }

    public static void validateValues(ShowTimeVo showTimeVo) throws InvalidDateException, InvalidHourException, InvalidRoomException {
        validDay(showTimeVo.getDay());
        validStartingHour(showTimeVo.getStartingSchedule());
        validRoomNumber(showTimeVo.getRoomId());
    }
}
