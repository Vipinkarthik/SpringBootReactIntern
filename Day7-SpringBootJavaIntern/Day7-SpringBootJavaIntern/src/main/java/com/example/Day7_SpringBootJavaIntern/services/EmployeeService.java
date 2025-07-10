package com.example.Day7_SpringBootJavaIntern.services;

import com.example.Day7_SpringBootJavaIntern.models.Employee;
import com.example.Day7_SpringBootJavaIntern.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setRole(updatedEmployee.getRole());
            return employeeRepository.save(employee);
        });
    }
}
