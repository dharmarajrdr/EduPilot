package com.edupilot.backend.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Option {

    private String content;
}
