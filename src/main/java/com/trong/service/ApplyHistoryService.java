package com.trong.service;

import com.trong.model.ApplyHistory;

public interface ApplyHistoryService {
    void save(ApplyHistory applyHistory);
    ApplyHistory findById(Long id);
}
