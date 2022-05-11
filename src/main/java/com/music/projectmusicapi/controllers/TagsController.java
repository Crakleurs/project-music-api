package com.music.projectmusicapi.controllers;

import com.music.projectmusicapi.dto.TagDto;
import com.music.projectmusicapi.entities.TagEntity;
import com.music.projectmusicapi.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("tag")
@RequiredArgsConstructor
public class TagsController {
    private final TagsService tagsService;

    @GetMapping()
    public List<TagEntity> getTags() {
        return this.tagsService.getTags();
    }

    @PostMapping()
    public TagEntity createTag(@RequestBody TagDto tagDto) {
        return this.tagsService.createTag(tagDto);
    }

    @PatchMapping("/{id}")
    public TagEntity updateTag(@PathVariable Long id, @RequestBody TagDto tagDto) {
        return this.tagsService.updateTag(id, tagDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        this.tagsService.deleteTag(id);
    }
}
