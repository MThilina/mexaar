package com.mexeer.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }
}
