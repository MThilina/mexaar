package com.mexeer.todo.dto;

import com.mexeer.todo.entity.User;
import lombok.Data;

@Data
public class ToDoListDTO {
    private Long id;
    private String name;
    private String description;
    private User user;
}
