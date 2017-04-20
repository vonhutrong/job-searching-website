package com.trong.repository;

import com.trong.model.Employee;
import com.trong.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(Long id);
    Employee findByUser(User user);
}
