package com.mexeer.todo.service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *     Common interface strand provide to do different kind of actions in the service layer
 * </p>
 * @param <T>
 * @param <ID>
 */
public interface GenericService <T,ID>{

    T create(T entity) throws Exception;

    T update (ID id,T entity) throws Exception;

    Optional<T> getById(ID id) throws Exception;

    List<T> getAll() throws Exception;

    void deleteById(ID id) throws Exception;
}
