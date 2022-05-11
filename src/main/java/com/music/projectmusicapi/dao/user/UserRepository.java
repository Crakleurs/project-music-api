package com.music.projectmusicapi.dao.user;

import com.music.projectmusicapi.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
