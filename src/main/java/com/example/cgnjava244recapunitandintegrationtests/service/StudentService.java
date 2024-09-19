package com.example.cgnjava244recapunitandintegrationtests.service;

import com.example.cgnjava244recapunitandintegrationtests.exceptions.StudentNotFoundException;
import com.example.cgnjava244recapunitandintegrationtests.model.Student;
import com.example.cgnjava244recapunitandintegrationtests.model.StudentDto;
import com.example.cgnjava244recapunitandintegrationtests.repo.StudentRepo;
import com.example.cgnjava244recapunitandintegrationtests.util.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo repo;
    private final UtilService util;


    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(String id) throws StudentNotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("No Student found with ID: "+id));
    }

    public Student createStudent(StudentDto newStudent) throws StudentNotFoundException {
        Student temp = new Student(util.generateRandomId(),
                newStudent.name(),
                newStudent.age());
        repo.save(temp);
        return repo.findById(temp.id())
                .orElseThrow(() -> new StudentNotFoundException("Student could not be saved, please try again"));
    }

    public Student updateStudent(String id, StudentDto updatedStudent) throws StudentNotFoundException {
        Student oldValues = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("No Student found with ID: "+id));
        Student newValues = oldValues.withName(updatedStudent.name()).withAge(updatedStudent.age());
        repo.save(newValues);
        return repo.findById(newValues.id())
                .orElseThrow(() -> new StudentNotFoundException("Student could not be updated, please try again"));
    }

    public Student deleteStudent(String id) throws StudentNotFoundException {
        Student studentToDelete = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("No Student found with ID: "+id));
        repo.deleteById(id);
        return studentToDelete;
    }
}
