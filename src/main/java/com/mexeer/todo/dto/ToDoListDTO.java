package com.mexeer.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mexeer.todo.entity.User;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToDoListDTO {
    private Long id;
    private String name;
    private String description;
    private User user;
}
