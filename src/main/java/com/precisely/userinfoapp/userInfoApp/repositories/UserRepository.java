package com.precisely.userinfoapp.userInfoApp.repositories;

import com.precisely.userinfoapp.userInfoApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //select * from work where id = :id
    Optional<User> findById(Long id);


}