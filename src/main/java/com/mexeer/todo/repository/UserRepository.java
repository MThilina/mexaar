package com.mexeer.todo.repository;

import com.mexeer.todo.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface UserRepository extends GenericRepository<User,Long> {
    boolean existsByEmail(@NotNull(message = "field should not be null") @NotEmpty(message = "field should not be blank") String email);
}
