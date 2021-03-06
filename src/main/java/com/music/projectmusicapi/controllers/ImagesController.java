package com.music.projectmusicapi.controllers;

import com.music.projectmusicapi.dto.ImageDto;
import com.music.projectmusicapi.entities.ImageEntity;
import com.music.projectmusicapi.services.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/list/{articleId}")
    public List<ImageEntity> getImages(@PathVariable Long articleId) {
        return this.imagesService.findImages(articleId);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) throws IOException {
        this.imagesService.deleteImage(id);
    }

    @PostMapping(value = "/{articleId}")
    public Iterable<ImageEntity> createImages(@PathVariable Long articleId, @RequestParam(value = "files",required = false) MultipartFile[] files) {
        return this.imagesService.createImages(articleId, files);
    }
}
