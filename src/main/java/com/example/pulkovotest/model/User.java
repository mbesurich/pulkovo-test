package com.example.pulkovotest.model;

import com.example.pulkovotest.model.enums.Status;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class User {

    public static final int ADULT_AGE = 18;

    @Id
    private Long id;

    private String name;

    @NotNull
    private LocalDate dateOfBirth;

    @Email
    private String email;

    @Enumerated
    private Status status;
}
