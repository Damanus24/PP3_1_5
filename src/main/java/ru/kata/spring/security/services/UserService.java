package ru.kata.spring.security.services;

import ru.kata.spring.security.models.Role;
import ru.kata.spring.security.models.User;
import java.util.List;

public interface UserService {

    public List<User> findAllUsers();

    public List<Role> findAllRoles();

    public User getUser(int id);

    public void saveUser(User user);

    public void updateUser(User updatedUser);

    public void deleteUser(int id);

    public boolean existsUserByEmail(String email);

}
