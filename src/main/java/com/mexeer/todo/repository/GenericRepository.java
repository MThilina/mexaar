package com.mexeer.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 *     Abstraction of the implementation of repository layer this avoid the code duplication
 * </p>
 * @param <T>
 * @param <ID>
 */
public interface GenericRepository <T,ID> extends JpaRepository<T,ID> {
}
