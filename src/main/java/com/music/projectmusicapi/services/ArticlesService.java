package com.music.projectmusicapi.services;

import com.music.projectmusicapi.dao.article.ArticleFactory;
import com.music.projectmusicapi.dto.ArticleDto;
import com.music.projectmusicapi.entities.ArticleEntity;
import com.music.projectmusicapi.dao.article.ArticleRepository;
import com.music.projectmusicapi.entities.ImageEntity;
import com.music.projectmusicapi.exceptions.HttpNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticlesService {
    private final ArticleRepository articleRepository;
    private final ImagesService imagesService;

    public ArticleEntity findOne(Long id) {
        Optional<ArticleEntity> articleEntity = this.articleRepository.findById(id);
        if (articleEntity.isEmpty())
            throw new HttpNotFoundException("L'article avec l'id " + id +" n'a pas été trouvé");

        return articleEntity.get();
    }

    public Iterable<ArticleEntity> findAll() {
        return this.articleRepository.findAll();
    }

    public ArticleEntity createArticle(ArticleDto articleDto) {
        ArticleEntity articleEntity = ArticleFactory.toEntity(articleDto);

        return this.articleRepository.save(articleEntity);
    }

    public void deleteArticle(Long id) {
        Optional<ArticleEntity> articleEntity = this.articleRepository.findById(id);
        if (articleEntity.isEmpty())
            throw new HttpNotFoundException("L'article avec l'id " + id +" n'a pas été trouvé");

        List<ImageEntity> imageEntities = articleEntity.get().getImages();
        this.imagesService.deleteImages(imageEntities);
    }


    public ArticleEntity updateArticle(Long id, ArticleDto articleDto) {
        Optional<ArticleEntity> articleEntity = this.articleRepository.findById(id);
        if (articleEntity.isEmpty())
            throw new HttpNotFoundException("L'article avec l'id " + id +" n'a pas été trouvé");

        articleEntity = Optional.of(ArticleFactory.updateToEntity(articleDto, articleEntity.get()));
        return this.articleRepository.save(articleEntity.get());
    }
}
