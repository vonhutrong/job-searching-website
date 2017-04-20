package com.trong.repository;

import com.trong.model.ApplyHistory;
import com.trong.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Long> {
    ApplyHistory findById(Long id);
    List<ApplyHistory> findByEmployeeOrderByDatetimeDesc(Employee employee);
}
