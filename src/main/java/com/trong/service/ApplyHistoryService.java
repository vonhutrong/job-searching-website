package com.trong.service;

import com.trong.model.ApplyHistory;
import com.trong.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplyHistoryService {
    void save(ApplyHistory applyHistory);
    ApplyHistory findById(Long id);

    Page<ApplyHistory> findByEmployee(Employee employee, Pageable pageable);
}
