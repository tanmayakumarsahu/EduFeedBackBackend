package com.edufeedback.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edufeedback.backend.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}