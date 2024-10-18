package com.mexeer.todo.implementation;

import com.mexeer.todo.entity.Task;
import com.mexeer.todo.entity.TaskStatus;
import com.mexeer.todo.entity.ToDoList;
import com.mexeer.todo.repository.TaskRepository;
import com.mexeer.todo.repository.ToDoListRepository;
import com.mexeer.todo.service.TaskService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class TaskServiceImplement implements TaskService {

    private final TaskRepository taskRepository;
    private final ToDoListRepository toDoListRepository;

    public TaskServiceImplement(TaskRepository taskRepository, ToDoListRepository toDoListRepository) {
        this.taskRepository = taskRepository;
        this.toDoListRepository = toDoListRepository;
    }


    @Override
    public Task createTask(Long todoId, Task task) {
        // Check if ToDo list exists
        ToDoList toDoList = toDoListRepository.findById(todoId)
                .orElseThrow(() -> new EntityNotFoundException("ToDoList with ID " + todoId + " not found."));

        // Set the ToDo list for the task
        task.setToDoList(toDoList);

        // Save the task to the database
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByToDoListId(Long todoId) {
        // Check if ToDo list exists
        if (!toDoListRepository.existsById(todoId)) {
            throw new EntityNotFoundException("ToDoList with ID " + todoId + " not found.");
        }

        // Return tasks for the specified ToDo list
        return taskRepository.findByToDoListId(todoId);
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        // Check if task exists before updating
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found."));

        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        existingTask.setStartDate(task.getStartDate());
        existingTask.setEndDate(task.getEndDate());
        existingTask.setStatus(task.getStatus());

        return taskRepository.save(existingTask);
    }

    @Override
    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        // Check if task exists before updating
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found."));

        existingTask.setStatus(status);

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        // Check if task exists before deletion
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found."));

        taskRepository.delete(task);
    }
}
