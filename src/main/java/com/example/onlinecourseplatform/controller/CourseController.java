package com.example.onlinecourseplatform.controller;

import com.example.onlinecourseplatform.entity.Course;
import com.example.onlinecourseplatform.entity.Instructor;
import com.example.onlinecourseplatform.repository.CourseRepository;
import com.example.onlinecourseplatform.repository.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseController(CourseRepository courseRepository,
                            InstructorRepository instructorRepository){
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }


    @PostMapping("/{instructorId}")
    public Course createCourse(@PathVariable Long instructorId,
                               @Valid @RequestBody Course course){
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @GetMapping
    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    @GetMapping("/instructor/{instructorId}")
    public List<Course> getCoursesByInstructor(@PathVariable Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    @GetMapping("/no-enrollments")
    public List<Course> getCoursesWithNoEnrollments() {
        return courseRepository.findCoursesWithNoEnrollments();
    }

    @GetMapping("/most-enrolled")
    public Course getMostEnrolledCourse() {
        return courseRepository.findMostEnrolledCourse()
                .orElseThrow(() -> new RuntimeException("No enrollments found"));
    }





}
