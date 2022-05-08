package com.music.projectmusicapi.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor
public class ReservationEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Date startingAt;

    @Column(nullable = false)
    private Date endingAt;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private ArticleEntity article;
}
