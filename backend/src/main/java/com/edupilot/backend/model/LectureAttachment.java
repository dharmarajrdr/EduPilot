package com.edupilot.backend.model;

import com.edupilot.backend.model.enums.FileType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.UUID;

@Data
@Embeddable
public class LectureAttachment {

    @Column(unique = true, nullable = false)
    private UUID fileId;

    @Column(nullable = false)
    private String fileName;

    @Enumerated(EnumType.STRING)
    private FileType fileType;
}
