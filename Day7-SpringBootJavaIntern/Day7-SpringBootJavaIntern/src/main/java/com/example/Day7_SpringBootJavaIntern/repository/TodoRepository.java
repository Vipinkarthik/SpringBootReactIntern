package com.example.Day7_SpringBootJavaIntern.repository;

import com.example.Day7_SpringBootJavaIntern.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByEmployeeId(Long employeeId);
}
