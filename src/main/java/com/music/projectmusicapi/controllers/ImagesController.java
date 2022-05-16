package com.music.projectmusicapi.controllers;

import com.music.projectmusicapi.dto.ImageDto;
import com.music.projectmusicapi.entities.ImageEntity;
import com.music.projectmusicapi.services.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;

@RestController
@Validated
@RequestMapping("image")
@RequiredArgsConstructor
public class ImagesController {
    private final ImagesService imagesService;

    @GetMapping("/{id}")
    public StreamingResponseBody getImage(@PathVariable Long id) throws IOException {
        return this.imagesService.findImage(id);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) throws IOException {
        this.imagesService.deleteImage(id);
    }

    @PostMapping("/{articleId}")
    public Iterable<ImageEntity> createImages(@PathVariable Long articleId, @RequestBody ImageDto imageDto) {
        return this.imagesService.createImages(articleId, imageDto);
    }
}
