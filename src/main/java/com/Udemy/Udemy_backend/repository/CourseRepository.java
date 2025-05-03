package com.Udemy.Udemy_backend.repository;

import com.Udemy.Udemy_backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTitleContainingIgnoreCase(String title); // Search case-insensitive

    // ✅ Check if a course with the same title already exists
    Optional<Course> findByTitle(String title);
}
