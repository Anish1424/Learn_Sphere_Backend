package com.Udemy.Udemy_backend.controller;

import com.Udemy.Udemy_backend.model.Course;
import com.Udemy.Udemy_backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/webdev-courses")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class CourseController {
    @Autowired
    private CourseService courseService;

    // Get all courses (including demoVideoUrl)
    @GetMapping
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    // Search courses by title
    @GetMapping("/search")
    public List<Course> searchCourses(@RequestParam String title) {
        return courseService.searchCourses(title);
    }

    // Add a new course (Now includes demoVideoUrl)
    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }
}
