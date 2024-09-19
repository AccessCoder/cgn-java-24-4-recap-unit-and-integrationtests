package com.example.cgnjava244recapunitandintegrationtests.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilService {

    public String generateRandomId(){
        return UUID.randomUUID().toString();
    }
}
