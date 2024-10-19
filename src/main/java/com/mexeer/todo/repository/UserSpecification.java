package com.mexeer.todo.repository;

import com.mexeer.todo.entity.User;
import com.mexeer.todo.entity.ToDoList;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Join;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {

    public static Specification<User> createSpecification(
            String firstName,
            String lastName,
            String email,
            String todoListName
    ) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (firstName != null && !firstName.isEmpty()) {
                predicates.add(builder.equal(root.get("firstName"), firstName));
            }

            if (lastName != null && !lastName.isEmpty()) {
                predicates.add(builder.equal(root.get("lastName"), lastName));
            }

            if (email != null && !email.isEmpty()) {
                predicates.add(builder.equal(root.get("email"), email));
            }

            if (todoListName != null && !todoListName.isEmpty()) {
                Join<User, ToDoList> toDoListJoin = root.join("toDoLists");
                predicates.add(builder.equal(toDoListJoin.get("name"), todoListName));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}