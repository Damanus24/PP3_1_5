package ru.kata.spring.security.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Size(min = 1, max = 15, message = "Имя должно содержать от 1 до 15 символов.")
    @NotBlank(message = "Это поле не должно быть пустым")
    private String name;

    @Column(name = "surname")
    @Size(min = 1, max = 25, message = "Фамилия должна содержать от 1 до 25 символов.")
    @NotBlank(message = "Это поле не должно быть пустым")
    private String surname;

    @Column(name = "age")
    @Min(value = 18, message = "Минимальный возраст 18 лет")
    @Max(value = 99, message = "Максимальный возраст 99 лет")
    private int age;

    @Column(name = "email", unique = true)
    @Email(message = "Неверный формат для этого поля")
    @NotBlank(message = "Это поле не должно быть пустым")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Это поле не должно быть пустым")
    private String password;

//    @ManyToMany(cascade = {/*CascadeType.PERSIST,*/ CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(String name, String surname, int age, String email, String password, Collection<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, email);
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + age + " " + email + " " + password + " " + roles;
    }

}

