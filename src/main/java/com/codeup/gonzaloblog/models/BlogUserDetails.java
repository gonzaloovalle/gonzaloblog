package com.codeup.gonzaloblog.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class BlogUserDetails extends User implements org.springframework.security.core.userdetails.UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public BlogUserDetails(long id, String username, String email, String password) {
        super(id, username, email, password);
    }
}
