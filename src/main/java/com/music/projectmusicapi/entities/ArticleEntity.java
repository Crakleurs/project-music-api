package com.music.projectmusicapi.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @OneToMany(fetch = FetchType.EAGER)
    private List<ImageEntity> images;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private CategoryEntity category;
}
