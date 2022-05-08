package com.music.projectmusicapi.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor
public class MessageEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String content;

    @Column
    private Date date;

    @ManyToOne
    private UserEntity sender;

    @ManyToOne
    private UserEntity receiver;
}
