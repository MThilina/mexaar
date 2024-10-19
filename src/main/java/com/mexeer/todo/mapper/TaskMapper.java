package com.mexeer.todo.mapper;

import com.mexeer.todo.dto.TaskDTO;
import com.mexeer.todo.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TaskMapper {
    @Mapping(source = "toDoList", target = "toDoList")
    TaskDTO toDTO(Task task);

    @Mapping(source = "toDoList", target = "toDoList")
    Task toEntity(TaskDTO taskDTO);
}
