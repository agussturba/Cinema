package com.endava.Cinema.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowTimeVo {
    Integer id;
    Integer movieId;
    Integer roomId;
    LocalDate day;
    String startingSchedule;
    String endingSchedule;

    public ShowTimeVo() {
    }


    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public void setStartingSchedule(String startingSchedule) {
        this.startingSchedule = startingSchedule;
    }

    public LocalTime getEndingSchedule() {
        return LocalTime.parse(endingSchedule);
    }

    public void setEndingSchedule(String endingSchedule) {
        this.endingSchedule = endingSchedule;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public LocalTime getStartingSchedule(){
        return LocalTime.parse(this.startingSchedule);
    }

    public Integer getId() {
        return id;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public Integer getRoomId() {
        return roomId;
    }


}
