package com.endava.Cinema.model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Movie {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String genre;
    private String duration;
    private String cast;
    private String director;
    private String summary;
    private Boolean PG;

    public Movie() {
    }

    public Movie(String title, String genre, String duration, String cast, String director, String summary, Boolean PG) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.cast = cast;
        this.director = director;
        this.summary = summary;
        this.PG = PG;
    }

    public Boolean getPG() {
        return PG;
    }

    public void setPG(Boolean PG) {
        this.PG = PG;
    }

    public LocalTime getDuration() {
        return LocalTime.parse(duration);
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
