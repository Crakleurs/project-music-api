package com.music.projectmusicapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter @Setter
public class UserDto {
    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @NonNull
    private String password;

}
