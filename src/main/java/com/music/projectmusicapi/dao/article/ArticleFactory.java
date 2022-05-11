package com.music.projectmusicapi.dao.article;

import com.music.projectmusicapi.dto.ArticleDto;
import com.music.projectmusicapi.entities.ArticleEntity;

public class ArticleFactory {
    public static ArticleEntity toEntity(ArticleDto articleDto) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setName(articleDto.getName());
        articleEntity.setDescription(articleDto.getDescription());
        return  articleEntity;
    }

    public static ArticleEntity updateToEntity(ArticleDto articleDto, ArticleEntity articleEntity) {
        articleEntity.setName(articleDto.getName());
        articleEntity.setDescription(articleDto.getDescription());
        return articleEntity;
    }
}
