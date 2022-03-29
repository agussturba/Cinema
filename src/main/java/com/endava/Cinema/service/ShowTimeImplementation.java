package com.endava.Cinema.service;

import com.endava.Cinema.exceptions.notFoundExceptions.MovieNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.ShowTimeNotFoundException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidDateException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidHourException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidRoomException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidScheduleException;
import com.endava.Cinema.model.Movie;
import com.endava.Cinema.model.ShowTime;
import com.endava.Cinema.persistance.MovieRepository;
import com.endava.Cinema.persistance.ShowTimeRepository;
import com.endava.Cinema.utilities.LocalTimesUtilities;
import com.endava.Cinema.vo.ShowTimeVo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.endava.Cinema.utilities.ShowTimeValidations.validateValues;

@Service
public class ShowTimeImplementation implements ShowTimeService {

    private final ShowTimeRepository showTimeRepository;

    private final MovieRepository movieRepository;

    public ShowTimeImplementation(ShowTimeRepository showTimeRepository, MovieRepository movieRepository) {
        this.showTimeRepository = showTimeRepository;
        this.movieRepository = movieRepository;
    }


    @Override
    public List<ShowTime> getShowTimesByMovieId(Integer movieId) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        return showTimeRepository.findByMovie(movie);
    }

    @Override
    public ShowTime getShowTimeById(Integer showTimeId) throws ShowTimeNotFoundException {
        return showTimeRepository.findById(showTimeId).orElseThrow(ShowTimeNotFoundException::new);
    }

    @Override
    public void deleteShowTimeById(Integer showTimeId) throws ShowTimeNotFoundException {
        ShowTime showTime = this.getShowTimeById(showTimeId);
        showTimeRepository.delete(showTime);
    }


    @Override
    public List<ShowTime> getAllShowTimes() {
        return (List<ShowTime>) showTimeRepository.findAll();
    }

    @Override
    public ShowTime saveOrUpdateShowTime(ShowTimeVo showTime) throws MovieNotFoundException, ShowTimeNotFoundException, InvalidScheduleException, InvalidDateException, InvalidHourException, InvalidRoomException {
        if (showTime.getId() != null) {
            this.getShowTimeById(showTime.getId());
        }
        Movie movie = movieRepository.findById(showTime.getMovieId()).orElseThrow(MovieNotFoundException::new);
        String newEndingSchedule = LocalTimesUtilities.sumLocalTimes(movie.getDuration(), showTime.getStartingSchedule());
        showTime.setEndingSchedule(newEndingSchedule);
        validateValues(showTime);
        validSchedule(showTime);
        ShowTime newShowTime = new ShowTime(movie, showTime.getRoomId(), showTime.getDay(), showTime.getStartingSchedule().toString());
        return showTimeRepository.save(newShowTime);
    }

    public List<ShowTime> getShowTimeByDayAndRoom(LocalDate day, Integer room) {
        return showTimeRepository.findByDayAndRoom(day, room);
    }

    private void validSchedule(ShowTimeVo showTimeVo) throws InvalidScheduleException {
        List<ShowTime> showTimeList = this.getShowTimeByDayAndRoom(showTimeVo.getDay(), showTimeVo.getRoomId());
        LocalTime newStartingSchedule = showTimeVo.getStartingSchedule();
        LocalTime newEndingSchedule = showTimeVo.getEndingSchedule();
        if (showTimeList != null) {
            for (ShowTime showTime : showTimeList) {
                int timeBetweenStartingAndEndingScheduled = localTimeToMinutes(showTime.getStartingSchedule()) - localTimeToMinutes(newEndingSchedule);
                int timeBetweenEndingAndStartingScheduled = localTimeToMinutes(newStartingSchedule) - localTimeToMinutes(showTime.getEndingSchedule());
                if (equalStartingSchedules(showTime, newStartingSchedule) && showTime.getId() != showTimeVo.getId()) {//case were they have matching starting hour
                    throw new InvalidScheduleException();
                } else if (isShowTimeInsideAnotherShowTime(showTimeVo, showTime)) {// case were new schedule has inside another schedule
                    throw new InvalidScheduleException();
                }else if (isStartingScheduleInsideAnotherShowTime(showTime,showTimeVo)){
                    throw new InvalidScheduleException();
                }
                else if (isEndingScheduleInAnotherShowTime(showTime, showTimeVo)) {
                    throw new InvalidScheduleException();
                } else if (timeBetweenStartingAndEndingScheduled < 20 && timeBetweenStartingAndEndingScheduled >= 0) {
                    throw new InvalidScheduleException();
                } else if (timeBetweenEndingAndStartingScheduled < 20 && timeBetweenEndingAndStartingScheduled >= 0) {
                    throw new InvalidScheduleException();
                }
            }
        }
    }

    private int localTimeToMinutes(LocalTime localTime) {
        int minutes = localTime.getMinute();
        int hours = localTime.getHour();
        return (60 * hours) + minutes;
    }

        private boolean isEndingScheduleInAnotherShowTime(ShowTime schedule, ShowTimeVo showTimeVo) {
        if (schedule.getStartingSchedule().isBefore(showTimeVo.getStartingSchedule()) && schedule.getEndingSchedule().isAfter(showTimeVo.getStartingSchedule())) {
            return true;
        }
        return false;
    }
    private boolean isStartingScheduleInsideAnotherShowTime(ShowTime schedule, ShowTimeVo showTimeVo){
        if(schedule.getStartingSchedule().isAfter(showTimeVo.getStartingSchedule()) && schedule.getStartingSchedule().isBefore(showTimeVo.getEndingSchedule())){
            return true;
        }
        return false;
    }
    private boolean equalStartingSchedules(ShowTime showTime, LocalTime startingSchedule) {
        if (showTime.getStartingSchedule().equals(startingSchedule)) {
            return true;
        }
        return false;
    }

    private boolean isShowTimeInsideAnotherShowTime(ShowTimeVo newShowTime, ShowTime showTime) {
        if (showTime.getStartingSchedule().isAfter(newShowTime.getStartingSchedule()) && showTime.getEndingSchedule().isBefore(newShowTime.getEndingSchedule())) {
            return true;
        }
        return false;
    }



}
