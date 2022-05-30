package com.music.projectmusicapi.services;

import com.music.projectmusicapi.dao.LikeRepository;
import com.music.projectmusicapi.dao.article.ArticleRepository;
import com.music.projectmusicapi.dao.user.UserRepository;
import com.music.projectmusicapi.entities.ArticleEntity;
import com.music.projectmusicapi.entities.LikeEntity;
import com.music.projectmusicapi.entities.UserEntity;
import com.music.projectmusicapi.exceptions.HttpBadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikesService {
    private LikeRepository likeRepository;
    private ArticleRepository articleRepository;
    private UserRepository userRepository;

    public LikesService(LikeRepository likeRepository, ArticleRepository articleRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public int countLikes(long articleId) {
        return this.likeRepository.countAllByArticle_Id(articleId);
    }

    public boolean isLiked(Authentication authentication, Long articleId) {
        return this.likeRepository.countAllByArticle_IdAndUser_Email(articleId, authentication.getName()) == 1;
    }

    public void like(Authentication authentication, Long articleId) {

        if (isLiked(authentication, articleId))
            this.likeRepository.deleteAllByArticle_IdAndUser_Email(articleId, authentication.getName());

        LikeEntity likeEntity = new LikeEntity();
        Optional<UserEntity> user = this.userRepository.findByEmail(authentication.getName());
        Optional<ArticleEntity> article = this.articleRepository.findById(articleId);
        if (!user.isPresent() || !article.isPresent())
            throw new HttpBadRequestException("Erreur");

        likeEntity.setArticle(article.get());
        likeEntity.setUser(user.get());
        this.likeRepository.save(likeEntity);
    }
}
