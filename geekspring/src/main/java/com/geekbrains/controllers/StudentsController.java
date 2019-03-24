package com.geekbrains.controllers;

import com.geekbrains.entities.Course;
import com.geekbrains.entities.Student;
import com.geekbrains.repositories.StudentsRepository;
import com.geekbrains.services.CoursesService;
import com.geekbrains.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private StudentsService studentsService;
    private CoursesService coursesService;
    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsController(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @RequestMapping("/list")
    public String showStudentsList(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsList();
        model.addAttribute("studentsList", allStudents);
        return "students-list";
    }

    @RequestMapping("sortedList")
    public String showStudentsSortedList(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsList();
        TreeMap<Student, Integer> map = new TreeMap<>((o1, o2) -> {
            Integer o1size = o1.getCourses().size();
            Integer o2size = o2.getCourses().size();
            return o1size.compareTo(o2size);
        });
        for (int i = 0; i < allStudents.size(); i++) {
            map.put(allStudents.get(i), allStudents.get(i).getCourses().size());
        }
        model.addAttribute("studentsMap", map);
        return "students-sorted-list";
    }

    //    private StudentsService studentsService;
//
//    @Autowired
//    public void setStudentsService(StudentsService studentsService) {
//        this.studentsService = studentsService;
//    }
//
//    public StudentsController() {
//    }
//
//    @RequestMapping("/showForm")
//    public String showSimpleForm(Model model) {
//        Student student = new Student();
//        model.addAttribute("student", student);
//        return "student-form";
//    }
//
//    @RequestMapping("/processForm")
//    public String processForm(@ModelAttribute("student") Student student) {
//        System.out.println(student.getFirstName() + " " + student.getLastName());
//        return "student-form-result";
//    }
//
    // http://localhost:8189/students/showStudentById?id=5
    @RequestMapping(path = "/showCoursesByStudentId", method = RequestMethod.GET)
    public String showStudentById(Model model, @RequestParam int id) {
        Optional<Student> student = studentsService.getStudentById((long) id);
        List<Course> courses = student.orElse(new Student()).getCourses();
        if (courses == null) {
            courses = new LinkedList<>();
        }
        model.addAttribute("courses", courses);
        return "student-courses-form";
    }

    @RequestMapping(path = "/showStudentsByCourseId", method = RequestMethod.GET)
    public String showCourseById(Model model, @RequestParam int id) {
        Optional<Course> course = coursesService.getCourseById((long) id);
        List<Student> students = course.orElse(new Course()).getStudents();
        if (students == null) {
            students = new LinkedList<>();
        }
        model.addAttribute("students", students);
        return "course-students-form";
    }
//
//    @RequestMapping(path="/getStudentById", method=RequestMethod.GET)
//    @ResponseBody
//    public Student getStudentById(@RequestParam int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        return student;
//    }
//
//    @RequestMapping(path="/getStudentById/{sid}", method=RequestMethod.GET)
//    @ResponseBody
//    public Student getStudentByIdFromPath(@PathVariable("sid") int id) {
//        Student student = studentsService.getStudentById(new Long(id));
//        return student;
//    }
}
