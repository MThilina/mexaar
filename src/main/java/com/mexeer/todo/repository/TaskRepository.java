package com.mexeer.todo.repository;

import com.mexeer.todo.entity.Task;

import java.util.List;

public interface TaskRepository extends GenericRepository<Task,Long> {
    List<Task> findByToDoListId(Long todoId);
}
