package com.edupilot.backend.repository;

import com.edupilot.backend.model.Comment;
import com.edupilot.backend.model.Lecture;
import com.edupilot.backend.model.LectureDiscussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureDiscussionRepository extends JpaRepository<LectureDiscussion, Long> {

    Optional<LectureDiscussion> findLectureDiscussionByComment(Comment comment);

    Optional<LectureDiscussion> findLectureDiscussionById(Long id);

    List<LectureDiscussion> findLectureDiscussionsByLectureOrderByIdDesc(Lecture lecture);
}
