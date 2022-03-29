package com.endava.Cinema.vo;

import java.util.ArrayList;
import java.util.List;

public class ReservationVo {
    Integer id;
    List<String> seatsList;
    Integer showTimeId;
    Integer userId;

    public ReservationVo() {
    }

    public Integer getId() {
        return id;
    }



    public Integer getShowTimeId() {
        return showTimeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setShowTimeId(Integer showTimeId) {
        this.showTimeId = showTimeId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<String> getSeatsList() {
        return seatsList;
    }

    public void setSeatsList(List<String> seatsList) {
        this.seatsList = seatsList;
    }
}
