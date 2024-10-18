package com.mexeer.todo.repository;

import com.mexeer.todo.entity.ToDoList;

import java.util.List;

public interface ToDoListRepository extends GenericRepository<ToDoList,Long> {
    List<ToDoList> findByUserId(Long userId);
}
