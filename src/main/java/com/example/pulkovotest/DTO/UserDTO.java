package com.example.pulkovotest.DTO;

import com.example.pulkovotest.model.enums.Status;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private Integer age;
    private String email;
    private Status previousStatus;
    private Status currentStatus;
}
