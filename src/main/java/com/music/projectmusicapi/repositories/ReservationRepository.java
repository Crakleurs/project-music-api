package com.music.projectmusicapi.repositories;

import com.music.projectmusicapi.entities.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
}
