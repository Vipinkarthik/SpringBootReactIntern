package com.example.Day7_SpringBootJavaIntern.controllers;

import com.example.Day7_SpringBootJavaIntern.models.Todo;
import com.example.Day7_SpringBootJavaIntern.repository.TodoRepository;
import com.example.Day7_SpringBootJavaIntern.models.Employee;
import com.example.Day7_SpringBootJavaIntern.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // ✅ Create a new Todo for a specific employee
    @PostMapping("/employee/{employeeId}")
    public Todo createTodoForEmployee(@PathVariable Long employeeId, @RequestBody Todo todo) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));
        todo.setEmployee(employee);
        return todoRepository.save(todo);
    }

    // ✅ Get all Todos
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // ✅ Get a Todo by ID
    @GetMapping("/{todoId}")
    public Todo getTodoById(@PathVariable Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found with ID: " + todoId));
    }

    // ✅ Update a Todo by ID
    @PutMapping("/{todoId}")
    public Todo updateTodo(@PathVariable Long todoId, @RequestBody Todo updatedTodo) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found with ID: " + todoId));
        todo.setDescription(updatedTodo.getDescription());
        todo.setDone(updatedTodo.isDone());
        return todoRepository.save(todo);
    }

    // ✅ Delete a Todo by ID
    @DeleteMapping("/{todoId}")
    public String deleteTodo(@PathVariable Long todoId) {
        if (!todoRepository.existsById(todoId)) {
            throw new RuntimeException("Todo not found with ID: " + todoId);
        }
        todoRepository.deleteById(todoId);
        return "Todo with ID " + todoId + " deleted successfully.";
    }

    // ✅ Get all Todos for a specific employee
    @GetMapping("/employee/{employeeId}")
    public List<Todo> getTodosByEmployee(@PathVariable Long employeeId) {
        return todoRepository.findByEmployeeId(employeeId);
    }
}
