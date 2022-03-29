package com.endava.Cinema.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.endava.Cinema.utilities.Pricing.seatValue;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    private List<Seat> seatList;
    @OneToOne
    @JoinColumn(name = "show_time_id")
    private ShowTime showTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Double price;

    public User getUser() {
        return user;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public Reservation() {
        seatList = new ArrayList<>();
    }

    public Reservation(List<Seat> seatList, ShowTime showTime, User user) {
        this.seatList = seatList;
        this.showTime = showTime;
        this.user = user;
        this.price = seatValue * seatList.size();
    }


    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeat(List<Seat> seats) {
        for (Seat seat :
                seats) {
            addSeat(seat);
        }
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public void addSeat(Seat seat) {
        seatList.add(seat);
        price += seatValue;
    }
}
