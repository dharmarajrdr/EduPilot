package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.TagNotFound;
import com.edupilot.backend.model.Tag;
import com.edupilot.backend.repository.TagRepository;
import com.edupilot.backend.service.interfaces.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    /**
     * @param tags
     * @return
     */
    @Override
    public List<Tag> saveAll(List<Tag> tags) {

        List<Tag> savedTags = new ArrayList<>();
        for (Tag tag : tags) {
            String name = tag.getName();
            tag = existByName(name) ? findTagByName(name) : tagRepository.save(tag);
            savedTags.add(tag);
        }
        return savedTags;
    }

    public Tag findTagByName(String name) {

        return tagRepository.findByName(name).orElseThrow(() -> new TagNotFound(name));
    }

    public Boolean existByName(String name) {

        return tagRepository.existsByName(name);
    }
}
