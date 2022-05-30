package com.music.projectmusicapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
public class ArticleDto {
    @NonNull
    String name;

    @NonNull
    String description;

    @NonNull
    Float priceByDay;

    @NonNull
    Long category;
}
