package com.music.projectmusicapi.controllers;

import com.music.projectmusicapi.services.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @GetMapping("/{articleId}")
    public int countLikes(@PathVariable Long articleId) {
        return this.likesService.countLikes(articleId);
    }

    @GetMapping("/me/{articleId}")
    public boolean isLiked(Authentication authentication, @PathVariable Long articleId) {
        return likesService.isLiked(authentication, articleId);
    }

    @PostMapping("/{articleId}")
    public void like(Authentication authentication, @PathVariable Long articleId) {
        likesService.like(authentication, articleId);
    }
}
