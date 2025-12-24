package com.example.onlinecourseplatform.repository;

import com.example.onlinecourseplatform.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}