package com.mexeer.todo.controller;

import com.mexeer.todo.entity.ToDoList;
import com.mexeer.todo.service.ToDoListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todolists")
@Tag(name = "ToDo List Management", description = "Operations related to managing ToDo lists")
public class ToDoListController {

    private final ToDoListService toDoListService;

    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @Operation(summary = "Create a new ToDo list", description = "Adds a new ToDo list for a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ToDo list successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/user/{userId}")
    public ResponseEntity<ToDoList> createToDoList(@PathVariable Long userId, @RequestBody ToDoList toDoList) {
        ToDoList createdToDoList = toDoListService.createToDoList(userId, toDoList);
        return new ResponseEntity<>(createdToDoList, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all ToDo lists by User ID", description = "Retrieves a list of all ToDo lists for a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "404", description = "ToDo lists not found")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ToDoList>> getToDoListsByUserId(@PathVariable Long userId) {
        List<ToDoList> toDoLists = toDoListService.getToDoListsByUserId(userId);
        return new ResponseEntity<>(toDoLists, HttpStatus.OK);
    }

    @Operation(summary = "Get ToDo list by ID", description = "Retrieves a ToDo list by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved ToDo list"),
            @ApiResponse(responseCode = "404", description = "ToDo list not found")
    })
    @GetMapping("/{toDoListId}")
    public ResponseEntity<ToDoList> getToDoListById(@PathVariable Long toDoListId) {
        Optional<ToDoList> toDoList = toDoListService.getToDoListById(toDoListId);
        return toDoList.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update ToDo list by ID", description = "Updates an existing ToDo list by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated ToDo list"),
            @ApiResponse(responseCode = "404", description = "ToDo list not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{toDoListId}")
    public ResponseEntity<ToDoList> updateToDoList(@PathVariable Long toDoListId, @RequestBody ToDoList toDoList) {
        ToDoList updatedToDoList = toDoListService.updateToDoList(toDoListId, toDoList);
        return updatedToDoList != null ? new ResponseEntity<>(updatedToDoList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete ToDo list by ID", description = "Deletes a ToDo list by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted ToDo list"),
            @ApiResponse(responseCode = "404", description = "ToDo list not found")
    })
    @DeleteMapping("/{toDoListId}")
    public ResponseEntity<Void> deleteToDoList(@PathVariable Long toDoListId) {
        toDoListService.deleteToDoList(toDoListId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
