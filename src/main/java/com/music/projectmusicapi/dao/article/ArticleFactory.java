package com.music.projectmusicapi.dao.article;

import com.music.projectmusicapi.dao.CategoryRepository;
import com.music.projectmusicapi.dto.ArticleDto;
import com.music.projectmusicapi.entities.ArticleEntity;
import com.music.projectmusicapi.entities.CategoryEntity;
import com.music.projectmusicapi.exceptions.HttpNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ArticleFactory {
    private final CategoryRepository categoryRepository;

    public ArticleEntity toEntity(ArticleDto articleDto) {
        Optional<CategoryEntity> categoryEntity = this.categoryRepository.findById(articleDto.getCategory());
        if (!categoryEntity.isPresent())
            throw new HttpNotFoundException("Pas de catégorie trouvée");

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setName(articleDto.getName());
        articleEntity.setDescription(articleDto.getDescription());
        articleEntity.setCategory(categoryEntity.get());
        articleEntity.setPriceByDay(articleDto.getPriceByDay());

        return  articleEntity;
    }

    public ArticleEntity updateToEntity(ArticleDto articleDto, ArticleEntity articleEntity) {
        Optional<CategoryEntity> categoryEntity = this.categoryRepository.findById(articleDto.getCategory());
        if (!categoryEntity.isPresent())
            throw new HttpNotFoundException("Pas de catégorie trouvée");

        articleEntity.setName(articleDto.getName());
        articleEntity.setDescription(articleDto.getDescription());
        articleEntity.setCategory(categoryEntity.get());
        return articleEntity;
    }
}
