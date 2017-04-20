package com.trong.repository;

import com.trong.model.ApplyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Long> {
    ApplyHistory findById(Long id);
}
