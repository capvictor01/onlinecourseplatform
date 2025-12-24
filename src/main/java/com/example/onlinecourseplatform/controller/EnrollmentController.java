package com.example.onlinecourseplatform.controller;


import com.example.onlinecourseplatform.entity.Course;
import com.example.onlinecourseplatform.entity.Enrollment;
import com.example.onlinecourseplatform.entity.EnrollmentStatus;
import com.example.onlinecourseplatform.entity.Student;
import com.example.onlinecourseplatform.repository.CourseRepository;
import com.example.onlinecourseplatform.repository.EnrollmentRepository;
import com.example.onlinecourseplatform.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentController(EnrollmentRepository enrollmentRepository,
                                StudentRepository studentRepository,
                                CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }



    @PostMapping("/{studentId}/{courseId}")
    public Enrollment enrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(EnrollmentStatus.ACTIVE);

        return enrollmentRepository.save(enrollment);
    }


    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrollment> getEnrollmentsByCourse(@PathVariable Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    @GetMapping("/active")
    public List<Enrollment> getActiveEnrollments() {
        return enrollmentRepository.findByStatus(EnrollmentStatus.ACTIVE);
    }

    @GetMapping("/completed")
    public List<Enrollment> getCompletedEnrollments() {
        return enrollmentRepository.findByStatus(EnrollmentStatus.COMPLETED);
    }

    @GetMapping("/course/{courseId}/students")
    public List<Student> getStudentsByCourse(@PathVariable Long courseId) {
        return enrollmentRepository
                .findByCourseIdAndStatus(courseId, EnrollmentStatus.ACTIVE)
                .stream()
                .map(Enrollment::getStudent)
                .toList();
    }




}
