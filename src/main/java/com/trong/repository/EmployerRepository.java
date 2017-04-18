package com.trong.repository;

import com.trong.model.Employer;
import com.trong.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Employer findByUser(User user);
    Employer findById(Long id);
}
