package com.example.todo.controller;

import com.example.todo.model.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class TodoController {
    
    // Thread-safe in-memory list
    private final List<Todo> todos = new CopyOnWriteArrayList<>();

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return todos;
    }

    @PostMapping("/todos")
    public Todo createTodo(@RequestBody Todo todo) {
        // Simple logic: if ID is missing, generate one.
        Todo newTodo = new Todo(
            todo.id() == null ? UUID.randomUUID().toString() : todo.id(),
            todo.title(),
            todo.completed()
        );
        todos.add(newTodo);
        return newTodo;
    }

    @GetMapping("/health")
    public String health() {
        return "{\"status\": \"UP\"}";
    }
}
