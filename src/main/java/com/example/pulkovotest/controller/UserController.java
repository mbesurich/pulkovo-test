package com.example.pulkovotest.controller;

import com.example.pulkovotest.DTO.ServerStatisticDTO;
import com.example.pulkovotest.DTO.UserDTO;
import com.example.pulkovotest.model.User;
import com.example.pulkovotest.model.enums.Status;
import com.example.pulkovotest.service.ServerStatisticService;
import com.example.pulkovotest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ServerStatisticService serverStatisticService;

    public UserController(UserService userService, ServerStatisticService serverStatisticService) {
        this.userService = userService;
        this.serverStatisticService = serverStatisticService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            User savedUser = userService.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Throwable e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getUserById(@RequestParam Long id) {
        try {
            User retrievedUser = userService.findById(id);
            return new ResponseEntity<>(userService.getUserDTOFromUser(retrievedUser), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}:togle_user_status")
    public ResponseEntity<?> toggleUserStatusById(@PathVariable Long id) {
        try {
            UserDTO userDTO = userService.toggleUserStatus(id);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/server_statistic")
    public ResponseEntity<?> getServerStatistic(@RequestParam Status status,
                                                @RequestParam(required = false)  boolean adult) {
        try {
            ServerStatisticDTO serverStatisticDTO = serverStatisticService.getServerStatisticDTO(status, adult);
            return new ResponseEntity<>(serverStatisticDTO, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
