package com.music.projectmusicapi.repositories;

import com.music.projectmusicapi.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
