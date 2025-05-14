package com.personal.learning.personal_learning.service;

import com.personal.learning.personal_learning.model.Todo;
import com.personal.learning.personal_learning.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> getAll() {
        return repo.findAll();
    }

    public Todo save(Todo todo) {
        return repo.save(todo);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
