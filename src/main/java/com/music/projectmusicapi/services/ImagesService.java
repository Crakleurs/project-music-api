package com.music.projectmusicapi.services;

import com.music.projectmusicapi.dao.article.ArticleRepository;
import com.music.projectmusicapi.dao.image.ImageRepository;
import com.music.projectmusicapi.entities.ArticleEntity;
import com.music.projectmusicapi.entities.ImageEntity;
import com.music.projectmusicapi.exceptions.HttpNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

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

    public ImageEntity getImage(Long id) {
        Optional<ImageEntity> imageEntity = this.imageRepository.findById(id);
        if (!imageEntity.isPresent())
            throw new HttpNotFoundException("L'image avec l'id " + id +" n'a pas été trouvée");

        return imageEntity.get();
    }

    public StreamingResponseBody findImage(Long id) {
        ImageEntity imageEntity = getImage(id);
        return outputStream -> {
            Files.copy(this.folder.resolve(imageEntity.getPath()), outputStream);
        };
    }


    public Iterable<ImageEntity> createImages(Long articleId, MultipartFile[] files) {
        Optional<ArticleEntity> articleEntity = this.articleRepository.findById(articleId);
        if (!articleEntity.isPresent())
            throw new HttpNotFoundException("L'article avec l'id " + articleId +" n'a pas été trouvé");

        List<ImageEntity> list = new ArrayList<>();
        Arrays.stream(files).forEach((file) -> {
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
        ImageEntity imageEntity = getImage(id);

        Files.delete(this.folder.resolve(imageEntity.getPath()));
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

    public List<ImageEntity> findImages(Long articleId) {
        return (List<ImageEntity>) this.imageRepository.findImageEntitiesByArticle_Id(articleId);
    }
}
