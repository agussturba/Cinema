package com.endava.Cinema.persistance;

import com.endava.Cinema.model.User;
import com.endava.Cinema.model.Reservation;
import com.endava.Cinema.model.ShowTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Integer> {
    List<Reservation> findByUser(User user);
    //Reservation findBySeatAndShowTime(Seat seat, ShowTime showTime); TODO preguntarle a lucas como se hace el query base method para buscar en una tabla intermedia.
    List<Reservation> findByShowTime(ShowTime showTime);
}
