package com.example.pulkovotest.service.impl;

import com.example.pulkovotest.DTO.UserDTO;
import com.example.pulkovotest.model.User;
import com.example.pulkovotest.model.enums.Status;
import com.example.pulkovotest.repository.UserRepository;
import com.example.pulkovotest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entity was not found with id = " + id));
    }

    @Override
    public UserDTO toggleUserStatus(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity was not found with id = " + id));
        Status previousStatus = user.getStatus();
        System.out.println("previousStatus - " + previousStatus);
        user.setStatus(previousStatus == Status.Online? Status.Offline : Status.Online);
        Status currentStatus = user.getStatus();
        System.out.println("currentStatus - " + currentStatus);
        return UserDTO.builder()
                .id(id)
                .previousStatus(previousStatus)
                .currentStatus(currentStatus)
                .build();
    }

    @Override
    public UserDTO getUserDTOFromUser(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .dateOfBirth(user.getDateOfBirth())
                .age(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears())
                .email(user.getEmail())
                .currentStatus(user.getStatus())
                .build();
    }
}
