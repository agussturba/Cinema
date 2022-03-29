package com.endava.Cinema.persistance;

import com.endava.Cinema.model.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat,String> {
}
