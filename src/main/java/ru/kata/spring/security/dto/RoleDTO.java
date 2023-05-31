package ru.kata.spring.security.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class RoleDTO {
    private int id;
    private String name;

    public RoleDTO(String name) {
        this.name = name;
    }
}

