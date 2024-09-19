package com.example.cgnjava244recapunitandintegrationtests.repo;

import com.example.cgnjava244recapunitandintegrationtests.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends MongoRepository<Student, String> {
}
