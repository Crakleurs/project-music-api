package com.music.projectmusicapi.services;

import com.music.projectmusicapi.dao.tag.TagFactory;
import com.music.projectmusicapi.dao.tag.TagRepository;
import com.music.projectmusicapi.dto.TagDto;
import com.music.projectmusicapi.entities.TagEntity;
import com.music.projectmusicapi.exceptions.HttpNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagsService {
    private final TagRepository tagRepository;

    public List<TagEntity> getTags() {
        return (List<TagEntity>) this.tagRepository.findAll();
    }

    public TagEntity createTag(TagDto tagDto) {
        TagEntity tagEntity = TagFactory.toEntity(tagDto);
        return this.tagRepository.save(tagEntity);
    }

    public TagEntity updateTag(Long id, TagDto tagDto) {
        TagEntity tag = getTag(id);
        TagEntity tagEntity = TagFactory.updateToEntity(tagDto, tag);
        return this.tagRepository.save(tagEntity);
    }

    public void deleteTag(Long id) {
        TagEntity tag = getTag(id);
        this.tagRepository.delete(tag);
    }

    public TagEntity getTag(Long id) {
        Optional<TagEntity> tag = this.tagRepository.findById(id);
        if (!tag.isPresent())
            throw new HttpNotFoundException("Le tag avec l'id " + id + " n'a pas été trouvé");
        return tag.get();
    }
}
