package com.example.onlinecourseplatform.controller;


import com.example.onlinecourseplatform.entity.Course;
import com.example.onlinecourseplatform.entity.Lesson;
import com.example.onlinecourseplatform.repository.CourseRepository;
import com.example.onlinecourseplatform.repository.LessonRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonController(LessonRepository lessonRepository,
                            CourseRepository courseRepository){
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/{courseId}")
    public Lesson createLesson(@PathVariable Long courseId,
                               @Valid @RequestBody Lesson lesson){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }

    @GetMapping
    public List<Lesson> getAllLessons(){
        return lessonRepository.findAll();
    }

    @GetMapping("/course/{courseId}")
    public List<Lesson> getLessonsByCourse(@PathVariable Long courseId) {
        return lessonRepository.findByCourseId(courseId);
    }


}
