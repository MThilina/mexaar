package com.mexeer.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * <p>
 *     Abstraction of the implementation of repository layer this avoid the code duplication
 * </p>
 * @param <T>
 * @param <ID>
 */

@NoRepositoryBean
public interface GenericRepository <T,ID> extends JpaRepository<T,ID> {
}
