package com.example.pulkovotest.service;

import com.example.pulkovotest.DTO.UserDTO;
import com.example.pulkovotest.model.User;

public interface UserService {

    User save(User user);
    User findById(Long id);
    UserDTO toggleUserStatus(Long id);
    UserDTO getUserDTOFromUser(User user);

}
