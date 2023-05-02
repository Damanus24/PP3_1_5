package ru.kata.spring.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.security.models.Role;
import ru.kata.spring.security.models.User;
import ru.kata.spring.security.repositories.RoleRepository;
import ru.kata.spring.security.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import ru.kata.spring.security.util.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //за транзанкционность отвечает JpaRepository
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        Optional<User> foundUser = userRepository.findById(id);
//        return foundUser.orElse(null);
        return foundUser.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void saveUser(User user) {
 /* Если "бикриптить" пароль здесь, а не в контроллере, то пароль во "вью" после создания user будет отображаться
 без "бикрипта" до обновления страницы. Скорее всего это связано с DTO слоем, так как при создании
 id отображается 0, а после обновления страницы он подгружается. Без DTO все работает адекватно.  */

//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User updatedUser) {
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
