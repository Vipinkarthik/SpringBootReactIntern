package com.example.Day5_SpringBootJavaIntern.Repository;

import com.example.Day5_SpringBootJavaIntern.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}