package com.codeup.gonzaloblog.repositories;

import com.codeup.gonzaloblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(long id);
    User findByUsername(String username);
}
