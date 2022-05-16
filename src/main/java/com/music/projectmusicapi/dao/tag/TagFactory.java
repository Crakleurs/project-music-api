package com.music.projectmusicapi.dao.tag;

import com.music.projectmusicapi.dto.TagDto;
import com.music.projectmusicapi.entities.TagEntity;

public class TagFactory {

    public static TagEntity toEntity(TagDto tagDto) {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(tagDto.getName());
        return tagEntity;
    }

    public static TagEntity updateToEntity(TagDto tagDto, TagEntity tagEntity) {
        tagEntity.setName(tagDto.getName());
        return tagEntity;
    }
}
