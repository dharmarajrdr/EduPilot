package com.edupilot.backend.repository;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    Optional<Lecture> findLectureByIdAndCourse(Long lectureId, Course course);

    Boolean existsLectureByChapterIdAndCourse(Integer chapterId, Course course);
}
