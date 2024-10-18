package com.mexeer.todo.service;

import com.mexeer.todo.entity.Task;
import com.mexeer.todo.constant.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskService extends GenericService<Task,Long>{
    Task createTask(Long todoId, Task task);

    List<Task> getTasksByToDoListId(Long todoId);

    Optional<Task> getTaskById(Long taskId);

    Task updateTask(Long taskId, Task task);

    Task updateTaskStatus(Long taskId, TaskStatus status);

    void deleteTask(Long taskId);
}
