package com.mexeer.todo.mapper;

import com.mexeer.todo.dto.UserDTO;
import com.mexeer.todo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
