package com.music.projectmusicapi.dao;
import com.music.projectmusicapi.entities.LikeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<LikeEntity, Long> {
    public int countAllByArticle_Id(Long articleId);

    public int countAllByArticle_IdAndUser_Email(Long articleId, String name);

    public void deleteAllByArticle_IdAndUser_Email(Long articleId, String name);

}
