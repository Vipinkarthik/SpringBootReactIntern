package com.example.Day7_SpringBootJavaIntern.controllers;

import com.example.Day7_SpringBootJavaIntern.models.Employee;
import com.example.Day7_SpringBootJavaIntern.models.Todo;
import com.example.Day7_SpringBootJavaIntern.repository.EmployeeRepository;
import com.example.Day7_SpringBootJavaIntern.repository.TodoRepository;
import com.example.Day7_SpringBootJavaIntern.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private EmployeeService employeeService;

    // 🔹 Create new employee
    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        return employeeRepository.save(emp);
    }

    // 🔹 Get all employees
    @GetMapping
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // 🔹 Get employee by ID
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    // 🔹 Update employee by ID
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee emp) {
        return employeeService.updateEmployee(id, emp)
                .orElseThrow(() -> new RuntimeException("Employee not found for update"));
    }

    // 🔹 Delete employee by ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found for deletion");
        }
        employeeRepository.deleteById(id);
        return "Employee deleted successfully with ID: " + id;
    }

    // 🔹 Find employees by role
    @GetMapping("/role/{role}")
    public List<Employee> getByRole(@PathVariable String role) {
        return employeeRepository.findByRole(role);
    }

    // 🔹 Add a new Todo for an employee
    @PostMapping("/{id}/todos")
    public Todo addTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        todo.setEmployee(emp);
        return todoRepository.save(todo);
    }

    // 🔹 Get all Todos of an employee
    @GetMapping("/{id}/todos")
    public List<Todo> getTodos(@PathVariable Long id) {
        return todoRepository.findByEmployeeId(id);
    }
}
