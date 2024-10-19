package com.mexeer.todo.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private List<ToDoListDTO> todoLists;
}
