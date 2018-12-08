package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/")
    public String index(Model model){
        //create a student
        Student student = new Student();
        student.setFirstname("Noah");
        student.setLastname("Perkins");

        //create a course
        Course course = new Course();
        course.setTitle("Intro to Java");
        course.setInstructor("Professor Henley");
        course.setDescription("Intro to Java with Spring Boot");
        course.setCredit(3);

        //add course to an empty list
        Set<Course> courses = new HashSet<Course>();
        courses.add(course);

        //Add list of courses to student's course list
        student.setCourses(courses);

        //Save student to database
        studentRepository.save(student);

        //Grab all students from database and send to template
        model.addAttribute("students", studentRepository.findAll());
        return "index";

    }
}
