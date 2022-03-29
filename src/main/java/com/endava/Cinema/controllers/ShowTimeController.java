package com.endava.Cinema.controllers;

import com.endava.Cinema.exceptions.notFoundExceptions.MovieNotFoundException;
import com.endava.Cinema.exceptions.notFoundExceptions.ShowTimeNotFoundException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidDateException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidHourException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidRoomException;
import com.endava.Cinema.exceptions.showTimeExceptions.InvalidScheduleException;
import com.endava.Cinema.model.ShowTime;
import com.endava.Cinema.service.ShowTimeService;
import com.endava.Cinema.vo.ShowTimeVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ShowTimeController {
    private final ShowTimeService showTimeService;

    public ShowTimeController(ShowTimeService showTimeService) {
        this.showTimeService = showTimeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")//OK
    public ResponseEntity<List<ShowTime>> getAllShowTimes() {
        return ResponseEntity.status(HttpStatus.OK).body(showTimeService.getAllShowTimes());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<ShowTime> getShowTimeById(@PathVariable Integer showTimeId) throws ShowTimeNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(showTimeService.getShowTimeById(showTimeId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ShowTime> saveShowTime(@RequestBody ShowTimeVo showTime) throws MovieNotFoundException, ShowTimeNotFoundException, InvalidScheduleException, InvalidDateException, InvalidHourException, InvalidRoomException {
        return ResponseEntity.status(HttpStatus.OK).body(showTimeService.saveOrUpdateShowTime(showTime));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('showTime:write')")
    public ResponseEntity<ShowTime> updateShowTime(@RequestBody ShowTimeVo showTime) throws ShowTimeNotFoundException, MovieNotFoundException, InvalidScheduleException, InvalidDateException, InvalidHourException, InvalidRoomException {
        return ResponseEntity.status(HttpStatus.OK).body(showTimeService.saveOrUpdateShowTime(showTime));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('showTime:write')")
    public void deleteShowTimeById(@PathVariable Integer showTimeId) throws ShowTimeNotFoundException {
        showTimeService.deleteShowTimeById(showTimeId);
    }
}
