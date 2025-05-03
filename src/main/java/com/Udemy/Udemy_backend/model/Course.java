package com.Udemy.Udemy_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses", uniqueConstraints = @UniqueConstraint(columnNames = "title")) // ✅ Make title unique
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // ✅ Ensure uniqueness in the database
    private String title;

    private String description;
    private String instructor;
    private double price;
    private String image;
    private String demoVideoUrl; // Ensure this field exists

    // Constructors
    public Course() {}

    public Course(String title, String description, String instructor, double price, String image, String demoVideoUrl) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.price = price;
        this.image = image;
        this.demoVideoUrl = demoVideoUrl;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getDemoVideoUrl() { return demoVideoUrl; }
    public void setDemoVideoUrl(String demoVideoUrl) { this.demoVideoUrl = demoVideoUrl; }
}
