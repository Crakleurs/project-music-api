package com.music.projectmusicapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageDto {
    @NonNull
    MultipartFile[] files;
}
