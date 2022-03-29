package com.endava.Cinema.service;

import com.endava.Cinema.exceptions.notFoundExceptions.MovieNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.ShowTimeNotFoundException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidDateException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidHourException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidRoomException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidScheduleException;
import com.endava.Cinema.model.ShowTime;
import com.endava.Cinema.vo.ShowTimeVo;

import java.util.List;

public interface ShowTimeService {
    List<ShowTime> getShowTimesByMovieId(Integer MovieId) throws MovieNotFoundException;

    ShowTime getShowTimeById(Integer ShowTimeId) throws ShowTimeNotFoundException;

    void deleteShowTimeById(Integer showTimeId) throws ShowTimeNotFoundException;

    List<ShowTime> getAllShowTimes();

    ShowTime saveOrUpdateShowTime(ShowTimeVo showTime) throws MovieNotFoundException, ShowTimeNotFoundException, InvalidScheduleException, InvalidDateException, InvalidHourException, InvalidRoomException;
}
