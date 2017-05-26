package com.trong.service;

import com.trong.model.ApplyHistory;
import com.trong.model.Employee;
import com.trong.model.Recruitment;
import com.trong.repository.ApplyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ApplyHistoryServiceImpl implements ApplyHistoryService {
    private final ApplyHistoryRepository applyHistoryRepository;

    @Autowired
    public ApplyHistoryServiceImpl(ApplyHistoryRepository applyHistoryRepository) {
        this.applyHistoryRepository = applyHistoryRepository;
    }

    @Override
    public void save(ApplyHistory applyHistory) {
        applyHistoryRepository.save(applyHistory);
    }

    @Override
    public ApplyHistory findById(Long id) {
        return applyHistoryRepository.findById(id);
    }

    @Override
    public Page<ApplyHistory> findByEmployee(Employee employee, Pageable pageable) {
        return applyHistoryRepository.findByEmployeeOrderByDatetimeDesc(employee, pageable);
    }

    @Override
    public ApplyHistory findByEmployeeAndRecruitment(Employee employee, Recruitment recruitment) {
        return applyHistoryRepository.findByEmployeeAndRecruitment(employee, recruitment);
    }
}
