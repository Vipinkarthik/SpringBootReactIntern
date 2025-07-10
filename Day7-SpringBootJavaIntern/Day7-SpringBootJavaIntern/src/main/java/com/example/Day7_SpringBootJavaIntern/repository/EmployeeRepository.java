package com.example.Day7_SpringBootJavaIntern.repository;

import com.example.Day7_SpringBootJavaIntern.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByRole(String role);
}
