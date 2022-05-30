package com.music.projectmusicapi.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LikeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ArticleEntity article;

    @ManyToOne
    private UserEntity user;
}
