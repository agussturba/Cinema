package com.endava.Cinema.model;

import com.endava.Cinema.utilities.LocalTimesUtilities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class ShowTime {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private Integer room;
    private LocalDate day;
    private String startingSchedule;
    private String endingSchedule;

    public ShowTime() {
    }

    public ShowTime(Movie movie, Integer room, LocalDate day, String startingSchedule) {//TODO TEST METHOD
        this.movie = movie;
        this.room = room;
        this.day = day;
        this.startingSchedule = startingSchedule;
        this.endingSchedule = LocalTimesUtilities.sumLocalTimes(this.getStartingSchedule(),movie.getDuration());
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getRoom() {
        return room;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getStartingSchedule() {
        return LocalTime.parse(startingSchedule);
    }

    public LocalTime getEndingSchedule() {
        return LocalTime.parse(endingSchedule);
    }



}
