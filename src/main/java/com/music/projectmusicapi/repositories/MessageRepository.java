package com.music.projectmusicapi.repositories;

import com.music.projectmusicapi.entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
}
