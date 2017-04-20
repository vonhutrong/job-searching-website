package com.trong.service;

import com.trong.model.ApplyHistory;
import com.trong.model.Employee;
import com.trong.repository.ApplyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyHistoryServiceImpl implements ApplyHistoryService {
    @Autowired
    private ApplyHistoryRepository applyHistoryRepository;

    @Override
    public void save(ApplyHistory applyHistory) {
        applyHistoryRepository.save(applyHistory);
    }

    @Override
    public ApplyHistory findById(Long id) {
        return applyHistoryRepository.findById(id);
    }

    @Override
    public List<ApplyHistory> findByEmployee(Employee employee) {
        return applyHistoryRepository.findByEmployeeOrderByDatetimeDesc(employee);
    }
}
