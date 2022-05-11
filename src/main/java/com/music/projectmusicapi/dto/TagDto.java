package com.music.projectmusicapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class TagDto {
    @NonNull
    private String name;
}
