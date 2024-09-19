package com.example.cgnjava244recapunitandintegrationtests.model;

import lombok.Data;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Students")
@With
public record Student(String id, String name, int age) {
}
