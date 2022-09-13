package com.precisely.userinfoapp.userInfoApp.repositories;

import com.precisely.userinfoapp.userInfoApp.domain.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

public interface DataTablesUserRepository extends DataTablesRepository<User, Integer> {

}
