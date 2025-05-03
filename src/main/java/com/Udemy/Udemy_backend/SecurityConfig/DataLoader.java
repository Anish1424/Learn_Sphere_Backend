package com.Udemy.Udemy_backend.config;

import com.Udemy.Udemy_backend.model.Course;
import com.Udemy.Udemy_backend.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(CourseRepository repository) {
        return args -> {
            addCourseIfNotExists(repository, "Full Stack Web Development",
                    "Learn HTML, CSS, JavaScript, React, and Node.js.",
                    "John Doe", 49.99,
                    "http://localhost:8081/images/fullstackweb.jpg",
                    "https://player.vimeo.com/video/1054941685");

            addCourseIfNotExists(repository, "React for Beginners",
                    "A step-by-step guide to building React applications.",
                    "Jane Smith", 39.99,
                    "http://localhost:8081/images/ReactCourse.jpg",
                    "https://player.vimeo.com/video/1054941685");
        };
    }

    // ✅ Strictly check for existing course before inserting
    private void addCourseIfNotExists(CourseRepository repository, String title, String description,
                                      String instructor, double price, String image, String demoVideoUrl) {
        Optional<Course> existingCourse = repository.findByTitle(title);

        if (existingCourse.isPresent()) {  // If course exists, don't insert
            System.out.println("Course already exists: " + title);
        } else {  // If course does NOT exist, insert it
            repository.save(new Course(title, description, instructor, price, image, demoVideoUrl));
            System.out.println("Inserted course: " + title);
        }
    }
}
