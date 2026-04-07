package com.edufeedback.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edufeedback.backend.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
