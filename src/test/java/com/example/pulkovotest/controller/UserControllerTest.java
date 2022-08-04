package com.example.pulkovotest.controller;

import com.example.pulkovotest.model.User;
import com.example.pulkovotest.model.enums.Status;
import com.example.pulkovotest.service.ServerStatisticService;
import com.example.pulkovotest.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private final User IVAN = new User(1L, "ivan", LocalDate.now().minusYears(1L), "ivan@mail.ru", Status.Offline);

    @Mock
    UserService userService;
    @Mock
    ServerStatisticService serverStatisticService;
    @InjectMocks
    UserController userController;

    @Test
    void success_saveUser_test() {
        // Given
//        User newUser = new User(1L, "ivan", LocalDate.now().minusYears(1L), "ivan@mail.ru", Status.Offline);
        // When
        Mockito.when(userService.save(IVAN)).thenReturn(IVAN);
        // Then
        assertAll(
                () -> assertThat(userController.saveUser(IVAN).getStatusCode()).isEqualTo(HttpStatus.CREATED)
//                () -> assertThat(userController.saveUser(IVAN).hasBody()).isTrue()
        );
    }

    @Test
    void fail_saveUser_test() {
        // Given
//        User newUser = new User(1L, "ivan", LocalDate.now().minusYears(1L), "ivan@mail.ru", Status.Offline);
        // When
        Mockito.when(userService.save(IVAN)).thenThrow(new RuntimeException("Entity was not found with id = " + 1L));
        // Then
        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, userController.saveUser(IVAN).getStatusCode())
//                () -> assertEquals(false, userController.saveUser(IVAN).hasBody())
        );
    }

    @Test
    void getUserById() {
    }

    @Test
    void toggleUserStatusById() {
    }

    @Test
    void getServerStatistic() {
    }
}