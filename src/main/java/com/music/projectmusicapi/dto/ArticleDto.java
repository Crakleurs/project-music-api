package com.music.projectmusicapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class ArticleDto {
    @NonNull
    String name;

    @NonNull
    String description;
}
