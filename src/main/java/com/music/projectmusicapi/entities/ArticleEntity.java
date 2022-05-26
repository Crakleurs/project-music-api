package com.music.projectmusicapi.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float priceByDay;

    @OneToMany
    private List<ImageEntity> images;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    private List<TagEntity> tags;

}
