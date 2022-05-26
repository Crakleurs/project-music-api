package com.music.projectmusicapi.services;


import com.music.projectmusicapi.dao.ReservationRepository;
import com.music.projectmusicapi.entities.ReservationEntity;
import com.music.projectmusicapi.exceptions.HttpNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationEntity getReservation(Long id) {
        Optional<ReservationEntity> reservationEntity = this.reservationRepository.findById(id);
        if (!reservationEntity.isPresent())
            throw new HttpNotFoundException("la reservation n'a pas étée trouvée");

        return reservationEntity.get();
    }
}
