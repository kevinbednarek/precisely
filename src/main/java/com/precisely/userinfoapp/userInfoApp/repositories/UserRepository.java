package com.precisely.userinfoapp.userInfoApp.repositories;

import com.precisely.userinfoapp.userInfoApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //select * from work where firstName = :firstName
    List<User> findByFirstName(String firstName);

    //select * from work where lastName = :lastName
    List<User> findByLastName(String lastName);

    //select * from work where dateOfBirth = :dateOfBirth
    List<User> findByDateOfBirth(Date dateOfBirth);


}