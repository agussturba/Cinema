package com.endava.Cinema.vo;

import com.endava.Cinema.model.Movie;

import java.time.LocalTime;
import java.util.Date;

public class MovieVo {
    Integer id;
    String title;
    String genre;
    String cast;
    String director;
    String summary;
    Boolean PG;
    String duration;

    public MovieVo() {
    }

    public MovieVo(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.genre = movie.getGenre();
        this.cast = movie.getCast();
        this.director = movie.getDirector();
        this.summary = movie.getSummary();
        this.PG = movie.getPG();
        this.duration = movie.getDuration().toString();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
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

    public Movie toModel() {
        Movie movie = new Movie();
        if (id != null) {
            movie.setId(id);
        }
        movie.setGenre(genre);
        movie.setTitle(title);
        movie.setCast(cast);
        movie.setDirector(director);
        movie.setPG(PG);
        movie.setSummary(summary);
        movie.setDuration(duration);
        return movie;
    }
}
