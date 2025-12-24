package com.example.onlinecourseplatform.repository;

import com.example.onlinecourseplatform.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
