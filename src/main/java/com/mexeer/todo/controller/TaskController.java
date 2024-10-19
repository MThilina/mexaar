package com.mexeer.todo.controller;

import com.mexeer.todo.dto.TaskDTO;
import com.mexeer.todo.entity.Task;
import com.mexeer.todo.constant.TaskStatus;
import com.mexeer.todo.mapper.TaskMapper;
import com.mexeer.todo.service.TaskService;
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
@RequestMapping("/api/tasks")
@Tag(name = "Task Management", description = "Operations related to managing tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Create a new task", description = "Adds a new task to the system for a specific ToDo list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/todo/{todoId}")
    public ResponseEntity<TaskDTO> createTask(@PathVariable Long todoId, @RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        Task createdTask = taskService.createTask(todoId, task);
        TaskDTO createdTaskDTO = taskMapper.toDTO(createdTask);
        return new ResponseEntity<>(createdTaskDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all tasks by ToDo list ID", description = "Retrieves a list of all tasks for a specific ToDo list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "404", description = "Tasks not found")
    })
    @GetMapping("/todo/{todoId}")
    public ResponseEntity<List<TaskDTO>> getTasksByToDoListId(@PathVariable Long todoId) {
        List<Task> tasks = taskService.getTasksByToDoListId(todoId);
        List<TaskDTO> taskDTOs = tasks.stream().map(taskMapper::toDTO).collect(Collectors.toList());
        return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get task by ID", description = "Retrieves a task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved task"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId) {
        Optional<Task> task = taskService.getTaskById(taskId);
        return task.map(value -> new ResponseEntity<>(taskMapper.toDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update task by ID", description = "Updates an existing task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated task"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        Task updatedTask = taskService.updateTask(taskId, task);
        return updatedTask != null ? new ResponseEntity<>(taskMapper.toDTO(updatedTask), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update task status by ID", description = "Updates the status of an existing task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated task status"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PatchMapping("/{taskId}/status")
    public ResponseEntity<TaskDTO> updateTaskStatus(@PathVariable Long taskId, @RequestParam TaskStatus status) {
        Task updatedTask = taskService.updateTaskStatus(taskId, status);
        return updatedTask != null ? new ResponseEntity<>(taskMapper.toDTO(updatedTask), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete task by ID", description = "Deletes a task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted task"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}