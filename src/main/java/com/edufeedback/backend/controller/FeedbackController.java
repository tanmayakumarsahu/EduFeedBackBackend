package com.edufeedback.backend.controller;

import com.edufeedback.backend.model.Feedback;
import com.edufeedback.backend.repository.FeedbackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping
    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    @PostMapping
    public Feedback create(@RequestBody Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @PutMapping("/{id}")
    public Feedback update(@PathVariable Long id, @RequestBody Feedback updated) {
        Feedback f = feedbackRepository.findById(id).orElseThrow();

        // Only update fields that are provided (non-null) — frontend sends partial updates
        if (updated.getCourse() != null) f.setCourse(updated.getCourse());
        if (updated.getInstructor() != null) f.setInstructor(updated.getInstructor());
        if (updated.getStudent() != null) f.setStudent(updated.getStudent());
        if (updated.getRating() != 0) f.setRating(updated.getRating());
        if (updated.getComment() != null) f.setComment(updated.getComment());

        return feedbackRepository.save(f);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        feedbackRepository.deleteById(id);
    }
}
