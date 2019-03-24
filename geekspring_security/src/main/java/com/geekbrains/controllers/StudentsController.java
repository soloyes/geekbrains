package com.geekbrains.controllers;

import com.geekbrains.entities.Course;
import com.geekbrains.entities.Student;
import com.geekbrains.services.CoursesService;
import com.geekbrains.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/students")
@Transactional
public class StudentsController {
    private StudentsService studentsService;
    private CoursesService coursesService;

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @RequestMapping("/list")
    @Transactional
    public String showStudentsList(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsList();
        model.addAttribute("studentsList", allStudents);
        return "students-list";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        Student student = new Student();
        student.setName("Unknown");
        model.addAttribute("student", student);
        return "add-student-form";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String showAddForm(Student student) {
        studentsService.addStudent(student);
        return "redirect:/students/list";
    }

    @RequestMapping(path = "/remove/{id}", method = RequestMethod.GET)
    public String removeById(@PathVariable(value = "id") Long studentId) {
        studentsService.removeById(studentId);
        return "redirect:/students/list";
    }

    @Secured({"ROLE_ADMIN"})
    @Transactional
    @RequestMapping(path = "/deleteCourse/{studentId}/{courseId}", method = RequestMethod.GET)
    public String removeCourseById(@PathVariable(value = "studentId") Long studentId, @PathVariable(value = "courseId") Long courseId) {
        Student student = studentsService.getStudentById(studentId);
        List<Course> courses = student.getCourses();
        Course course = null;
        for (Course c : courses) {
            if (c.getId().equals(courseId)) {
                course = c;
            }
        }
        student.getCourses().remove(course);
        return "students-list";
    }

    @Secured({"ROLE_ADMIN"})
    @Transactional
    @RequestMapping(path = "/addCourse/{studentId}/{courseId}", method = RequestMethod.GET)
    public String addCourseById(@PathVariable(value = "studentId") Long studentId, @PathVariable(value = "courseId") Long courseId) {
        Student student = studentsService.getStudentById(studentId);
        List<Course> courses = coursesService.getAllCoursesList();
        Course course = null;
        for (Course c : courses) {
            if (c.getId().equals(courseId)) {
                course = c;
            }
        }
        student.getCourses().add(course);
        return "students-list";
    }

    @RequestMapping(path = "/{id}/edu", method = RequestMethod.GET)
    public String showStudentById(@PathVariable(value = "id") Long id, Model model) {
        Student student = studentsService.getStudentById(new Long(id));
        List<Course> courses = coursesService.getAllCoursesList();
        List<Course> studentCourses = student.getCourses();
        courses.removeAll(studentCourses);
        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        return "student-courses";
    }
}