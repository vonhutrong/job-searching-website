package com.trong.service;

import com.trong.model.ApplyHistory;
import com.trong.repository.ApplyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyHistoryServiceImpl implements ApplyHistoryService {
    @Autowired
    private ApplyHistoryRepository applyHistoryRepository;

    @Override
    public void save(ApplyHistory applyHistory) {
        applyHistoryRepository.save(applyHistory);
    }
}
