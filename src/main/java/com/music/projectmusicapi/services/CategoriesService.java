package com.music.projectmusicapi.services;

import com.music.projectmusicapi.dao.CategoryRepository;
import com.music.projectmusicapi.entities.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoryRepository categoryRepository;


    public List<CategoryEntity> findAll() {
        return (List<CategoryEntity>) this.categoryRepository.findAll();
    }

}
