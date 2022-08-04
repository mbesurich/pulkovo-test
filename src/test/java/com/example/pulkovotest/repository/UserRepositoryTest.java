package com.example.pulkovotest.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @InjectMocks
    UserRepository userRepository;

    @Test
    void findById() {
//        assertEquals();
    }

    @Test
    void countAll() {
    }

    @Test
    void findAllByStatus() {
    }

    @Test
    void findAllByAgeGreaterThan() {
    }

    @Test
    void findAllByStatusAndAgeGreaterThan() {
    }
}