package com.mexeer.todo.implementation;

import com.mexeer.todo.entity.ToDoList;
import com.mexeer.todo.entity.User;
import com.mexeer.todo.repository.ToDoListRepository;
import com.mexeer.todo.repository.UserRepository;
import com.mexeer.todo.service.ToDoListService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class ToDoListImplementation implements ToDoListService {

    private final ToDoListRepository toDoListRepository;
    private final UserRepository userRepository;

    public ToDoListImplementation(ToDoListRepository toDoListRepository, UserRepository userRepository) {
        this.toDoListRepository = toDoListRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ToDoList createToDoList(Long userId, ToDoList toDoList) {
        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found."));

        // Set the user for the ToDo list
        toDoList.setUser(user);

        // Save the ToDo list to the database
        return toDoListRepository.save(toDoList);
    }

    @Override
    public List<ToDoList> getToDoListsByUserId(Long userId) {
        // Check if user exists
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with ID " + userId + " not found.");
        }

        // Return ToDo lists for the specified user
        return toDoListRepository.findByUserId(userId);
    }

    @Override
    public Optional<ToDoList> getToDoListById(Long toDoListId) {
        return toDoListRepository.findById(toDoListId);
    }

    @Override
    public ToDoList updateToDoList(Long toDoListId, ToDoList toDoList) {
        // Check if ToDo list exists before updating
        ToDoList existingToDoList = toDoListRepository.findById(toDoListId)
                .orElseThrow(() -> new EntityNotFoundException("ToDoList with ID " + toDoListId + " not found."));

        existingToDoList.setName(toDoList.getName());
        existingToDoList.setDescription(toDoList.getDescription());

        return toDoListRepository.save(existingToDoList);
    }

    @Override
    public void deleteToDoList(Long toDoListId) {
        // Check if ToDo list exists before deletion
        ToDoList toDoList = toDoListRepository.findById(toDoListId)
                .orElseThrow(() -> new EntityNotFoundException("ToDoList with ID " + toDoListId + " not found."));

        toDoListRepository.delete(toDoList);
    }
}
