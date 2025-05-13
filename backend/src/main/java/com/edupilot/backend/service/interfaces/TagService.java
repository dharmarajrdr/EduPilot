package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> saveAll(List<Tag> tags);
}
