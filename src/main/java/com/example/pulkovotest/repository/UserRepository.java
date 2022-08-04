package com.example.pulkovotest.repository;

import com.example.pulkovotest.model.User;
import com.example.pulkovotest.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

//    @Query("SELECT COUNT(u.id) from User u")
//    int userCount();
//
//    List<User> findAllByStatus();
//
//    @Query("""
//            SELECT u FROM User u
//            WHERE u.status = :status AND u.dateOfBirth > :dateOfBirth
//            """)
//    List<User> findAllUsersAccordingToStatusAndAge(@Param("status") Status status,
//                                                   @Param("dateOfBirth") LocalDate dateOfBirth);

    Integer countAll();
    List<User> findAllByStatus(Status status);
    List<User> findAllByAgeGreaterThan(Integer age);
    List<User> findAllByStatusAndAgeGreaterThan(Status status, Integer age);
}
