package com.music.projectmusicapi.repositories;

import com.music.projectmusicapi.entities.ImageEntity;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
}
