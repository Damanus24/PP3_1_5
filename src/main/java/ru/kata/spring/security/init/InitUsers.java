package ru.kata.spring.security.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.security.models.Role;
import ru.kata.spring.security.models.User;
import ru.kata.spring.security.repositories.RoleRepository;
import ru.kata.spring.security.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class InitUsers implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitUsers(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Role roleAdmin = new Role("ROLE_ADMIN");

            Role roleUser = new Role("ROLE_USER");
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);

            User user = new User();
            user.setName("User");
            user.setSurname("Userov");
            user.setAge(18);
            user.setEmail("user@mail.ru");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRoles(List.of(roleUser));
            userRepository.save(user);

            User admin = new User();
            admin.setName("Admin");
            admin.setSurname("Adminov");
            admin.setAge(99);
            admin.setEmail("admin@mail.ru");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(List.of(roleAdmin, roleUser));
            userRepository.save(admin);
        }
    }
}
