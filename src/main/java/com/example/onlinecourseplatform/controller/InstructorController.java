package com.example.onlinecourseplatform.controller;


import com.example.onlinecourseplatform.entity.Instructor;
import com.example.onlinecourseplatform.repository.InstructorRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    @PostMapping
    public Instructor createInstructor(@Valid @RequestBody Instructor instructor){
        return instructorRepository.save(instructor);
    }

    @GetMapping
    public List<Instructor> getAllInstructors(){
        return instructorRepository.findAll();
    }
}
