package com.music.projectmusicapi.services;

import com.music.projectmusicapi.dao.image.ImageRepository;
import com.music.projectmusicapi.dao.article.ArticleRepository;
import com.music.projectmusicapi.dto.ImageDto;
import com.music.projectmusicapi.entities.ArticleEntity;
import com.music.projectmusicapi.entities.ImageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImagesService {
    private final ImageRepository imageRepository;
    private final ArticleRepository articleRepository;

    private final Path folder = Paths.get("files");


    public StreamingResponseBody getImage(Long id) throws IOException {
        Optional<ImageEntity> imageEntity = this.imageRepository.findById(id);
        if (!imageEntity.isPresent())
            throw new HTTPException(404);

        return outputStream -> {
            Files.copy(this.folder.resolve(imageEntity.get().getPath()), outputStream);
        };
    }


    public Iterable<ImageEntity> createImages(Long articleId, ImageDto imageDto) {
        Optional<ArticleEntity> articleEntity = this.articleRepository.findById(articleId);
        if (!articleEntity.isPresent())
            throw new HTTPException(400);

        List<ImageEntity> list = new ArrayList<>();
        Arrays.stream(imageDto.getFiles()).forEach((file) -> {
            try {
                ImageEntity imageEntity = storeImage(articleEntity.get(), file);
                list.add(imageEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return imageRepository.saveAll(list);
    }

    public ImageEntity storeImage(ArticleEntity articleEntity, MultipartFile file) throws IOException {

        String filePath = UUID.randomUUID() + file.getOriginalFilename();
        Files.copy(file.getInputStream(), this.folder.resolve(filePath));
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setPath(filePath);
        imageEntity.setName(file.getOriginalFilename());
        imageEntity.setArticle(articleEntity);
        return imageEntity;
    }

    public void deleteImage(Long id) throws IOException {
        Optional<ImageEntity> imageEntity = this.imageRepository.findById(id);

        if (!imageEntity.isPresent())
            throw new HTTPException(404);

        Files.delete(this.folder.resolve(imageEntity.get().getPath()));
        this.imageRepository.deleteById(id);
    }

    public void deleteImages(List<ImageEntity> imageEntities) {
        imageEntities.forEach(imageEntity -> {
            try {
                Files.delete(this.folder.resolve(imageEntity.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        this.imageRepository.deleteAll(imageEntities);
    }
}
