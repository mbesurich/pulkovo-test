package com.example.pulkovotest.service.impl;

import com.example.pulkovotest.DTO.ServerStatisticDTO;
import com.example.pulkovotest.model.User;
import com.example.pulkovotest.model.enums.Status;
import com.example.pulkovotest.repository.UserRepository;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServerStatisticServiceDTOImplTest {

    private final User IVAN = new User(1L, "ivan", LocalDate.now().minusYears(1L), "ivan@mail.ru", Status.Offline);
    private final User PETER = new User(2L, "Petr", LocalDate.now().minusYears(20L), "petr@mail.ru", Status.Online);
    private final User VASIL = new User(3L, "Vasil", LocalDate.now().minusYears(21L), "vasil@mail.ru", Status.Offline);

    @Mock
    UserRepository userRepository;

    @InjectMocks
    ServerStatisticServiceDTOImpl serverStatisticServiceDTO;

    //    @BeforeTestClass
//    public static void before(){
//
//    }
//

//    @BeforeAll
//    public void beforeMethod() {
//        Mockito.when(userRepository.findAllByStatusAndAgeGreaterThan(Status.Offline, User.ADULT_AGE))
//                .thenReturn(Arrays.asList(
//                        new User(1L, "ivan", LocalDate.now(), "ivan@mail.ru", Status.Offline),
//                        new User(2L, "Petr", LocalDate.now(), "petr@mail.ru", Status.Offline),
//                        new User(3L, "Vasil", LocalDate.now(), "vasil@mail.ru", Status.Offline)
//                ));
//
//        Mockito.when(userRepository.findAllByStatus(Status.Offline))
//                .thenReturn(Arrays.asList(
//                        new User(1L, "ivan", LocalDate.now(), "ivan@mail.ru", Status.Offline),
//                        new User(2L, "Petr", LocalDate.now(), "petr@mail.ru", Status.Offline),
//                        new User(3L, "Vasil", LocalDate.now(), "vasil@mail.ru", Status.Offline)
//                ));
//
//        Mockito.when(userRepository.findAll())
//                .thenReturn(Arrays.asList(
//                        new User(1L, "ivan", LocalDate.now(), "ivan@mail.ru", Status.Offline),
//                        new User(2L, "Petr", LocalDate.now(), "petr@mail.ru", Status.Online),
//                        new User(3L, "Vasil", LocalDate.now(), "vasil@mail.ru", Status.Offline)
//                ));
//
//        Mockito.when(userRepository.findAllByAgeGreaterThan(User.ADULT_AGE))
//                .thenReturn(Arrays.asList(
//                        new User(1L, "ivan", LocalDate.now(), "ivan@mail.ru", Status.Offline),
//                        new User(2L, "Petr", LocalDate.now(), "petr@mail.ru", Status.Online),
//                        new User(3L, "Vasil", LocalDate.now(), "vasil@mail.ru", Status.Offline)
//                ));
//    }

    @Test
    void getServerStatisticDTO_without_parameters() {
        // Given
        List<User> userList = Arrays.asList(IVAN, PETER, VASIL);

        // When
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>(userList));

        // Then
        ServerStatisticDTO retrievedServerStatisticDTO = serverStatisticServiceDTO.getServerStatisticDTO(false);

        assertAll(
                () -> assertThat(retrievedServerStatisticDTO).isNotNull(),
                () -> assertThat(retrievedServerStatisticDTO.getQuantityOfUsers()).isEqualTo(3),
                () -> assertThat(retrievedServerStatisticDTO.getAverageAge()).isEqualTo(14.00000001, Offset.offset(0.00001))
        );
    }

//    @ParameterizedTest
//    @MethodSource("getParamsForGetServerStatisticDTO_test")
//    void GetServerStatisticDTO_test(Status status, boolean adult, ServerStatisticDTO serverStatisticDTO) {
//        List<User> userList = Arrays.asList(IVAN, PETER, VASIL);
//        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>(userList));
//        assertThat(serverStatisticServiceDTO.getServerStatisticDTO())
//    }
//
//    static Stream<Arguments> getParamsForGetServerStatisticDTO_test() {
//        List<User> userList = Arrays.asList(IVAN, PETER, VASIL);
//        return Stream.of(
//                Status.Online, true, ServerStatisticDTO(3, userList, )
//        );
//    }
}