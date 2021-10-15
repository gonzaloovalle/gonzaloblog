package com.codeup.gonzaloblog.services;

import com.codeup.gonzaloblog.models.User;
import com.codeup.gonzaloblog.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository usersDao;

    public UserService(UserRepository usersDao) {
        this.usersDao = usersDao;
    }

    public User getLoggedInUser() {
        return usersDao.findAll().get(0);
    }
}
