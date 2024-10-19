package com.mexeer.todo.mapper;

import com.mexeer.todo.dto.ToDoListDTO;
import com.mexeer.todo.entity.ToDoList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ToDoListMapper {
    @Mapping(source = "user", target = "user")
    ToDoListDTO toDTO(ToDoList toDoList);

    @Mapping(source = "user", target = "user")
    ToDoList toEntity(ToDoListDTO toDoListDTO);
}
