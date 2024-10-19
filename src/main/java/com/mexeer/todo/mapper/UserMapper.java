package com.mexeer.todo.mapper;

import com.mexeer.todo.dto.UserDTO;
import com.mexeer.todo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(source = "todoLists", target = "todoLists")
    UserDTO toDTO(User user);

    @Mapping(source = "todoLists", target = "todoLists")
    User toEntity(UserDTO userDTO);
}
