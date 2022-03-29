package com.endava.Cinema;

import com.endava.Cinema.utilities.PersistSeats;
import com.endava.Cinema.utilities.PersistUsers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.endava.Cinema.utilities.PersistSeats.seatsPersist;
import static com.endava.Cinema.utilities.PersistUsers.adminUserPersist;
import static com.endava.Cinema.utilities.PersistUsers.clientUserPersist;


@SpringBootApplication
public class CinemaApplication {

    public static void main(String[] args) {
        //seatsPersist();
        //adminUserPersist();
        //clientUserPersist();
        SpringApplication.run(CinemaApplication.class, args);
    }




}
