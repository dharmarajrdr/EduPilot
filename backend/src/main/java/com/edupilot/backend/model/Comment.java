package com.edupilot.backend.model;

import com.edupilot.backend.model.base.AuditCreation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Comment extends AuditCreation {

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String message;

    public boolean isAuthor(User user) {

        return this.user.equals(user);
    }
}
