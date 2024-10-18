package com.mexeer.todo.service;

import com.mexeer.todo.entity.ToDoList;

import java.util.List;
import java.util.Optional;

public interface ToDoListService extends GenericService<ToDoList,Long> {
    ToDoList createToDoList(Long userId, ToDoList toDoList);

    List<ToDoList> getToDoListsByUserId(Long userId);

    Optional<ToDoList> getToDoListById(Long toDoListId);

    ToDoList updateToDoList(Long toDoListId, ToDoList toDoList);

    void deleteToDoList(Long toDoListId);
}
