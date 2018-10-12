package com.geekbrains.services;

import com.geekbrains.entities.Course;
import com.geekbrains.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {
    private CourseRepository courseRepository;

    @Autowired
    public void setCoursesRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CoursesService() {
    }

    public List<Course> getAllCoursesList() {
        return (List<Course>) courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long aLong) {
        return courseRepository.findById(aLong);
    }
}
