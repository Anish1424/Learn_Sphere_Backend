package com.Udemy.Udemy_backend.service;

import com.Udemy.Udemy_backend.model.Course;
import com.Udemy.Udemy_backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    // Fetch all courses (including demoVideoUrl)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Search courses by title
    public List<Course> searchCourses(String title) {
        return courseRepository.findByTitleContainingIgnoreCase(title);
    }

    // Add a new course (Now includes demoVideoUrl)
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }
}
