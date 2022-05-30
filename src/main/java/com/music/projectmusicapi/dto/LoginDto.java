package com.music.projectmusicapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter @Setter
public class LoginDto {
    @NonNull
    private String email;

    @NonNull
    private String password;
}
