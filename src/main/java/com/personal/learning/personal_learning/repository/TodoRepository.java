package com.personal.learning.personal_learning.repository;

import com.personal.learning.personal_learning.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
