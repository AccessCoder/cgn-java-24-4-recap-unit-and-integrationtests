package com.example.cgnjava244recapunitandintegrationtests.service;

import com.example.cgnjava244recapunitandintegrationtests.exceptions.StudentNotFoundException;
import com.example.cgnjava244recapunitandintegrationtests.model.Student;
import com.example.cgnjava244recapunitandintegrationtests.model.StudentDto;
import com.example.cgnjava244recapunitandintegrationtests.repo.StudentRepo;
import com.example.cgnjava244recapunitandintegrationtests.util.UtilService;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private final StudentRepo mockRepo = mock(StudentRepo.class);
    private final UtilService mockUtil = mock(UtilService.class);

    private final StudentService service = new StudentService(mockRepo, mockUtil);


    @Test
    public void getAllStudent_shouldReturnEmptyList_whenCalledInitially(){
        //GIVEN
        when(mockRepo.findAll()).thenReturn(Collections.EMPTY_LIST);
        //WHEN
        List<Student> actual = service.getAllStudents();
        //THEN
        assertEquals(Collections.EMPTY_LIST, actual);

    }

    @Test
    public void getStudentById_shouldReturnStudent1_whenCalledWithValidId() throws StudentNotFoundException {
        //GIVEN
        Student expected = new Student("1", "Max", 25);
        when(mockRepo.findById(expected.id())).thenReturn(Optional.of(expected));
        //WHEN
        Student actual = service.getStudentById(expected.id());
        //THEN
        assertEquals(expected, actual);
        verify(mockRepo.findById("1"));
    }

    @Test
    public void getStudentById_shouldReturnStudent1_whenCalledWithInvalidId() {
        //GIVEN
        when(mockRepo.findById("1")).thenReturn(Optional.empty());
        //WHEN
        try {
            service.getStudentById("1");
            fail();
        //THEN
        }catch (StudentNotFoundException e){
            assertTrue(true);
        }

    }

    @Test
    public void createStudent_shouldReturnStudent_whenCalled() throws StudentNotFoundException {
        //GIVEN
        StudentDto studentDto = new StudentDto("Max", 25);
        Student expected = new Student("1", "Max", 25);
        when(mockRepo.findById("1")).thenReturn(Optional.of(expected));
        when(mockUtil.generateRandomId()).thenReturn("1");
        //WHEN
        Student actual = service.createStudent(studentDto);
        //THEN
        assertEquals(expected, actual);
        verify(mockRepo).save(expected);
    }

    @Test
    public void createStudent_shouldThrowException_whenNewStudentNotFound()  {
        //GIVEN
        StudentDto studentDto = new StudentDto("Max", 25);
        Student expected = new Student("1", "Max", 25);
        when(mockRepo.findById("1")).thenReturn(Optional.empty());
        when(mockUtil.generateRandomId()).thenReturn("1");
        //WHEN & THEN
        assertThrows(StudentNotFoundException.class, () -> service.createStudent(studentDto));
        verify(mockRepo).save(expected);
    }

    @Test
    public void updateStudent_shouldReturnUpdatedStudent_whenCalledWithValidId() throws StudentNotFoundException {
        //GIVEN
        StudentDto newValues = new StudentDto("Maxi", 26);
        Student expected = new Student("1", "Maxi", 26);
        when(mockRepo.findById("1")).thenReturn(Optional.of(new Student("1", "Max", 25)), Optional.of(expected));

        //WHEN
        Student actual = service.updateStudent("1", newValues);
        //THEN
        assertEquals(expected , actual);
        verify(mockRepo).save(expected);
    }
}