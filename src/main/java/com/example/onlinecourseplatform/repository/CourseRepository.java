package com.example.onlinecourseplatform.repository;

import com.example.onlinecourseplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructorId(Long instructorId);
    @Query("SELECT c FROM Course c WHERE c.id NOT IN (SELECT e.course.id FROM Enrollment e)")
    List<Course> findCoursesWithNoEnrollments();
    @Query("""
    SELECT c FROM Course c
    LEFT JOIN Enrollment e ON e.course = c
    GROUP BY c
    ORDER BY COUNT(e.id) DESC
    """)
    Optional<Course> findMostEnrolledCourse();




}
