package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import com.edupilot.backend.model.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "users")
public class User extends AuditCreation {

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String displayName;

    @OneToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Reply> replies;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;
}
