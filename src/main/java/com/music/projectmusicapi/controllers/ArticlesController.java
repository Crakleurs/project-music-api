package com.music.projectmusicapi.controllers;

import com.music.projectmusicapi.dto.ArticleDto;
import com.music.projectmusicapi.entities.ArticleEntity;
import com.music.projectmusicapi.services.ArticlesService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticlesService articlesService;

    @GetMapping("/{id}")
    public ArticleEntity findOne(@PathVariable Long id) {
        return this.articlesService.findOne(id);
    }

    @GetMapping("/items")
    public Iterable<ArticleEntity> findAll() {
        return this.articlesService.findAll();
    }

    @GetMapping("/djs")
    public Iterable<ArticleEntity> findAllDjs() {
        return this.articlesService.findAll();
    }

    @PostMapping()
    public ArticleEntity createArticle(@RequestBody ArticleDto articleDto) {
        return this.articlesService.createArticle(articleDto);
    }

    @PatchMapping("/{id}")
    public ArticleEntity editArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto) {
        return this.articlesService.updateArticle(id, articleDto);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        this.articlesService.deleteArticle(id);
    }

}
