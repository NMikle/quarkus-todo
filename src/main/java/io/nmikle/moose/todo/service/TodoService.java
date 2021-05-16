package io.nmikle.moose.todo.service;

import io.nmikle.moose.todo.model.TodoDto;

import java.util.Collection;
import java.util.Optional;

public interface TodoService {

    TodoDto save(TodoDto model);

    Collection<TodoDto> findAll();

    Optional<TodoDto> findById(Long id);

}
