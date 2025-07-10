package com.example.Day7_SpringBootJavaIntern.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private boolean done;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore // prevent infinite loop in serialization
    private Employee employee;

    public Todo() {
    }

    public Todo(Long id, String description, boolean done, Employee employee) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // ✅ Optional Getter to return employee name in response
    public String getEmployeeName() {
        return employee != null ? employee.getName() : null;
    }
}
