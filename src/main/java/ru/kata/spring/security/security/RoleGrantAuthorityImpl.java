package ru.kata.spring.security.security;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.security.models.Role;


public class RoleGrantAuthorityImpl implements GrantedAuthority {

    private final Role role;

    public RoleGrantAuthorityImpl(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getName();
    }
}
