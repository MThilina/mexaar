package com.mexeer.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "field should not be null") @NotEmpty(message = "field should not be blank")
    private String firstName;
    @NotNull(message = "field should not be null") @NotEmpty(message = "field should not be blank")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull(message = "field should not be null") @NotEmpty(message = "field should not be blank")
    private String email;
    @NotNull(message = "field should not be null") @NotEmpty(message = "field should not be blank")
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<TodoList> todoLists;
}

