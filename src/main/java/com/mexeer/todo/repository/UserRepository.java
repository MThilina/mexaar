package com.mexeer.todo.repository;

import com.mexeer.todo.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User,Long>, JpaSpecificationExecutor<User> {
    boolean existsByEmail(@NotNull(message = "field should not be null") @NotEmpty(message = "field should not be blank") String email);

    Optional<User> findByEmail(String email);
}
