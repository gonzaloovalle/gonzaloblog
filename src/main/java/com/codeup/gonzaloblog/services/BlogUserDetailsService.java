package com.codeup.gonzaloblog.services;

import com.codeup.gonzaloblog.models.BlogUserDetails;
import com.codeup.gonzaloblog.models.User;
import com.codeup.gonzaloblog.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BlogUserDetailsService implements UserDetailsService {

    public final UserRepository usersDao;

    public BlogUserDetailsService(UserRepository usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User details not found for user: " + username);
        } else {
            return new BlogUserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        }
    }
}
