package ru.kata.spring.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.security.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
