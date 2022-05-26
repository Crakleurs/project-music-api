package com.music.projectmusicapi.dao.article;

import com.music.projectmusicapi.dao.tag.TagRepository;
import com.music.projectmusicapi.dto.ArticleDto;
import com.music.projectmusicapi.entities.ArticleEntity;
import com.music.projectmusicapi.entities.TagEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArticleFactory {
    private final TagRepository tagRepository;

    public ArticleEntity toEntity(ArticleDto articleDto) {
        Iterable<TagEntity> tagEntities = this.tagRepository.findAllById(articleDto.getTags());
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setName(articleDto.getName());
        articleEntity.setDescription(articleDto.getDescription());
        articleEntity.setTags((List<TagEntity>) tagEntities);
        return  articleEntity;
    }

    public ArticleEntity updateToEntity(ArticleDto articleDto, ArticleEntity articleEntity) {
        Iterable<TagEntity> tagEntities = this.tagRepository.findAllById(articleDto.getTags());
        articleEntity.setName(articleDto.getName());
        articleEntity.setDescription(articleDto.getDescription());
        articleEntity.setTags((List<TagEntity>) tagEntities);
        return articleEntity;
    }
}
