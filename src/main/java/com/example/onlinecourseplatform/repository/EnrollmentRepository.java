package com.example.onlinecourseplatform.repository;

import com.example.onlinecourseplatform.entity.Enrollment;
import com.example.onlinecourseplatform.entity.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
    List<Enrollment> findByStatus(EnrollmentStatus status);
    List<Enrollment> findByCourseIdAndStatus(Long courseId, EnrollmentStatus status);




}
