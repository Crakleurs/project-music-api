package com.music.projectmusicapi.dao.tag;

import com.music.projectmusicapi.entities.TagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<TagEntity, Long> {
}
