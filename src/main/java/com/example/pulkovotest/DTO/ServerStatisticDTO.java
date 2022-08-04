package com.example.pulkovotest.DTO;

import com.example.pulkovotest.model.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerStatisticDTO {

    private Long quantityOfUsers;
    private List<User> usersInRequestedParams;
    private Double averageAge;

}
