package com.example.pulkovotest.service.impl;

import com.example.pulkovotest.model.User;
import com.example.pulkovotest.model.enums.Status;
import com.example.pulkovotest.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private final User newUser = new User(1L, "ivan", LocalDate.now().minusYears(1L), "ivan@mail.ru", Status.Offline);

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("method save should save new User")
    void save() {
        // Given
//        User newUser = new User(1L, "ivan", LocalDate.now().minusYears(1L), "ivan@mail.ru", Status.Offline);
        // When
        when(userRepository.save(newUser)).thenReturn(newUser);
        // Then
        assertEquals(newUser.getId(), userService.save(newUser).getId());
    }

    @Test
    void toggleUserStatus() {
        // Given
        User newUser = new User(1L, "ivan", LocalDate.now().minusYears(1L), "ivan@mail.ru", Status.Offline);
        // When
        when(userRepository.findById(1L)).thenReturn(Optional.of(newUser));
        // Then
        assertEquals(Status.Online, userService.toggleUserStatus(1L).getCurrentStatus());
    }

    @Test
    void getUserDTOFromUser() {
        // Given
//        User newUser = new User(1L, "ivan", LocalDate.now().minusYears(1L), "ivan@mail.ru", Status.Offline);
        // When
        // Then
        assertEquals(1, userService.getUserDTOFromUser(newUser).getAge());
    }

    @Nested
    @DisplayName("test findByTest different cases")
    class findByTest {

        @Test
        @DisplayName("findById should execute successfully")
        void findById_success_test() {
            // Given
//        User newUser = new User(1L, "ivan", LocalDate.now().minusYears(1L), "ivan@mail.ru", Status.Offline);
            // When
            when(userRepository.findById(1L)).thenReturn(Optional.of(newUser));
            // Then
            assertEquals(newUser.getName(), userService.findById(1L).getName());
        }

        @Test
        @DisplayName("findById should fail IllegalArgumentException")
        void findById_fail_test() {
            // Given
            // When
            when(userRepository.findById(anyLong())).thenThrow(IllegalArgumentException.class);
//        when(userRepository.findById(null)).thenThrow(IllegalArgumentException.class);
            // Then
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> userService.findById(100L)),
                    () -> assertThrows(IllegalArgumentException.class, () -> userService.findById(null))
            );
        }

    }
}