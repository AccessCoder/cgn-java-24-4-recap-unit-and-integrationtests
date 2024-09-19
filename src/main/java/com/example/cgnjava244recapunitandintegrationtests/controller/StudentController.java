package com.example.cgnjava244recapunitandintegrationtests.controller;

import com.example.cgnjava244recapunitandintegrationtests.exceptions.StudentNotFoundException;
import com.example.cgnjava244recapunitandintegrationtests.model.Student;
import com.example.cgnjava244recapunitandintegrationtests.model.StudentDto;
import com.example.cgnjava244recapunitandintegrationtests.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping
    public List<Student> getAllStudents(){
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) throws StudentNotFoundException {
        return service.getStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentDto newStudent) throws StudentNotFoundException {
        return service.createStudent(newStudent);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id,
                                 @RequestBody StudentDto updatedStudent) throws StudentNotFoundException {
        return service.updateStudent(id, updatedStudent);
    }

    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable String id) throws StudentNotFoundException {
        return service.deleteStudent(id);
    }
}
