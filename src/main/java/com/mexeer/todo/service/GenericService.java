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

    default T create(T entity) throws Exception {return null;}

    default T update(ID id, T entity) throws Exception {return null;}

    default Optional<T> getById(ID id) throws Exception {return null;}

    default List<T> getAll() throws Exception {return null;}

    default void deleteById(ID id) throws Exception {}
}
