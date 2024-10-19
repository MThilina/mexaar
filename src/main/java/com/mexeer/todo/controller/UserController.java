package com.mexeer.todo.controller;

import com.mexeer.todo.dto.UserDTO;
import com.mexeer.todo.entity.User;
import com.mexeer.todo.mapper.UserMapper;
import com.mexeer.todo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Operations related to managing users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new User", description = "Adds a new user to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws Exception {
        User user = userMapper.toEntity(userDTO);
        User createdUser = userService.create(user);
        UserDTO createdUserDTO = userMapper.toDTO(createdUser);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all Users", description = "Retrieves a list of all users in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() throws Exception {
        List<User> users = userService.getAll();
        List<UserDTO> userDTOs = users.stream().map(userMapper::toDTO).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get User by ID", description = "Retrieves a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) throws Exception {
        Optional<User> user = userService.getById(userId);
        return user.map(value -> new ResponseEntity<>(userMapper.toDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update User by ID", description = "Updates an existing user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated user"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) throws Exception {
        User user = userMapper.toEntity(userDTO);
        User updatedUser = userService.update(userId, user);
        return updatedUser != null ? new ResponseEntity<>(userMapper.toDTO(updatedUser), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete User by ID", description = "Deletes a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws Exception {
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}