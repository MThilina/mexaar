package com.mexeer.todo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class TodoList extends BaseEntity{

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "toDoList",cascade = CascadeType.ALL)
    private List<Task> tasks;
}
