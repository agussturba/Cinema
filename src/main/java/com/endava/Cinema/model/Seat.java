package com.endava.Cinema.model;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private String section;
    @ManyToOne
    @JoinColumn(name = "previous_seat_id")
    private Seat previousSeat;

    public Seat() {
    }

    public Seat(String id, String sectionSeat, Seat previousSeat) {
        this.id = id;
        this.section = sectionSeat;
        this.previousSeat = previousSeat;
    }


    public Seat getPreviousSeat() {
        return previousSeat;
    }

    public void setPreviousSeat(Seat previousSeat) {
        this.previousSeat = previousSeat;
    }

    public String getSection() {
        return section;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSection(String section) {
        this.section = section;
    }

}
