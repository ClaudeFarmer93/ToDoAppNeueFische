package org.example.todoappneuefische.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdService {
    public static String generateRandomI(){
        return UUID.randomUUID().toString();
    }
}
