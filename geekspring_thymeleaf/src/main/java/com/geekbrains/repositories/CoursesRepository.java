package com.geekbrains.repositories;

import com.geekbrains.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CoursesRepository extends CrudRepository<Course, Long> {
}
