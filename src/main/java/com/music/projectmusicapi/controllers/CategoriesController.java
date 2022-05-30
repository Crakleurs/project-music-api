package com.music.projectmusicapi.controllers;

import com.music.projectmusicapi.entities.CategoryEntity;
import com.music.projectmusicapi.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;

    @GetMapping()
    public List<CategoryEntity> findAll() {
        return this.categoriesService.findAll();
    }
}
