package ru.kata.spring.security.dto;

import lombok.*;
import ru.kata.spring.security.models.Role;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private int id;

    @Size(min = 1, max = 15, message = "Имя должно содержать от 1 до 15 символов.")
    @NotBlank(message = "Это поле не должно быть пустым")
    private String name;

    @Size(min = 1, max = 25, message = "Фамилия должна содержать от 1 до 25 символов.")
    @NotBlank(message = "Это поле не должно быть пустым")
    private String surname;

    @Min(value = 18, message = "Минимальный возраст 18 лет")
    @Max(value = 99, message = "Максимальный возраст 99 лет")
    private int age;

    @Email(message = "Неверный формат для этого поля")
    @NotBlank(message = "Это поле не должно быть пустым")
    private String email;

    @NotBlank(message = "Это поле не должно быть пустым")
    private String password;

    private Collection<Role> roles;
}
