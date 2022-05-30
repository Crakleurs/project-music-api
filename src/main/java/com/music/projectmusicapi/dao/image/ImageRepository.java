package com.music.projectmusicapi.dao.image;

import com.music.projectmusicapi.entities.ImageEntity;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
    public Iterable<ImageEntity> findImageEntitiesByArticle_Id(Long articleId);
}
