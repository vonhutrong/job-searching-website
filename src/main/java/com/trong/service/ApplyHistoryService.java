package com.trong.service;

import com.trong.model.ApplyHistory;
import com.trong.model.Employee;

import java.util.List;

public interface ApplyHistoryService {
    void save(ApplyHistory applyHistory);
    ApplyHistory findById(Long id);
    List<ApplyHistory> findByEmployee(Employee employee);
}
