package com.music.projectmusicapi.controllers;

import com.music.projectmusicapi.entities.ReservationEntity;
import com.music.projectmusicapi.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("reservation")
@RequiredArgsConstructor
public class ReservationController {
    private ReservationService reservationService;

    @GetMapping("/{id}")
    public ReservationEntity getReservation(@PathVariable Long id) {
        return this.reservationService.getReservation(id);
    }
}
