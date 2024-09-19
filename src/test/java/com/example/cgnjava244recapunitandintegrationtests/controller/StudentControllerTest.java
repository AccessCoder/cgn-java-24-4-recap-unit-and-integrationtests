package com.example.cgnjava244recapunitandintegrationtests.controller;

import com.example.cgnjava244recapunitandintegrationtests.model.Student;
import com.example.cgnjava244recapunitandintegrationtests.repo.StudentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StudentRepo repo;


    @Test
    void getAllStudents_shouldReturnEmptyList_whenCalledInitially() throws Exception {
        //GIVEN
        //WHEN & THEN
        mvc.perform(get("/api/student"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    void getStudentById_shouldReturnStudent1_whenCalledWithValidId() throws Exception {
        //GIVEN
        Student student = new Student("1", "Max", 25);
        repo.save(student);
        //WHEN & THEN
        mvc.perform(get("/api/student/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                                                                {
                                                                    "id": "1",
                                                                    "name": "Max",
                                                                    "age": 25
                                                                }
                                                                """));
    }

    @Test
    void createStudent_shouldReturnCreatedStudent_whenCalled() throws Exception {
        //GIVEN
        //WHEN & THEN
        mvc.perform(post("/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Max",
                            "age": 25
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                                                                {
                                                                    "name": "Max",
                                                                    "age": 25
                                                                }
                                                                """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

    }
}