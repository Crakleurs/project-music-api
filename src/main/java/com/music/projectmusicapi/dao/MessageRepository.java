package com.music.projectmusicapi.dao;

import com.music.projectmusicapi.entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
}
