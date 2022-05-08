package com.music.projectmusicapi.repositories;

import com.music.projectmusicapi.entities.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
}
