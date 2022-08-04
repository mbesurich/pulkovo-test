package com.example.pulkovotest.service.impl;

/**
 * @author Mikhail Besedin
 * @version 1.0
 * This class get server statistic according to requested parameters:
 * Status and Age (2 options related to boolean "adult": true means >= 18 years from current date or less than 18 years).
 * Both parameters are not required.
 */

import com.example.pulkovotest.DTO.ServerStatisticDTO;
import com.example.pulkovotest.model.User;
import com.example.pulkovotest.model.enums.Status;
import com.example.pulkovotest.repository.UserRepository;
import com.example.pulkovotest.service.ServerStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Transactional
public class ServerStatisticServiceDTOImpl implements ServerStatisticService {

    private final UserRepository userRepository;

    @Autowired
    public ServerStatisticServiceDTOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *
     * @param status
     * @param adult boolean flag: true means >= 18 years from current date or less than 18 years)
     * @throws IllegalArgumentException if there is no any users matching the parameters in the argument of this method
     * @return ServerStatisticDTO with set parameters: Integer quantityOfUsers, List of User usersInRequestedParams and Double averageAge
     */
    @Override
    public ServerStatisticDTO getServerStatisticDTO(Status status, boolean adult) throws IllegalArgumentException{

        List<User> allUsersByParamRequest;
        if (status != null && !adult) {
            allUsersByParamRequest = userRepository.findAllByStatusAndAgeGreaterThan(status, User.ADULT_AGE);
        } else if (status != null) {
            allUsersByParamRequest = userRepository.findAllByStatus(status);
        } else if (!adult) {
            allUsersByParamRequest = userRepository.findAllByAgeGreaterThan(User.ADULT_AGE);
        } else {
            allUsersByParamRequest = userRepository.findAll();
        }

        return ServerStatisticDTO.builder()
                .quantityOfUsers((long) allUsersByParamRequest.size())
                .usersInRequestedParams(allUsersByParamRequest)
                .averageAge(
                        allUsersByParamRequest
                                .stream()
                                .map(User::getDateOfBirth)
                                .mapToInt(localDate -> Period.between(localDate, LocalDate.now()).getYears())
                                .average()
                                .orElseThrow(IllegalArgumentException::new)
                )
                .build();
    }

//    @Override
    public ServerStatisticDTO getServerStatisticDTO(boolean adult) {

        List<User> allUsersByParamRequest;
        if (adult) {
            allUsersByParamRequest = userRepository.findAllByAgeGreaterThan(User.ADULT_AGE);
        } else {
            allUsersByParamRequest = userRepository.findAll();
        }

        return ServerStatisticDTO.builder()
                .quantityOfUsers((long) allUsersByParamRequest.size())
                .usersInRequestedParams(allUsersByParamRequest)
                .averageAge(
                        allUsersByParamRequest
                                .stream()
                                .map(User::getDateOfBirth)
                                .mapToInt(dateOfBirth -> Period.between(dateOfBirth, LocalDate.now()).getYears())
                                .average()
                                .orElseThrow(IllegalArgumentException::new)
                )
                .build();
    }
}
