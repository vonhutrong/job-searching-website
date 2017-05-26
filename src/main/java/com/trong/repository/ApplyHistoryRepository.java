package com.trong.repository;

import com.trong.model.ApplyHistory;
import com.trong.model.Employee;
import com.trong.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Long> {
    ApplyHistory findById(Long id);
    Page<ApplyHistory> findByEmployeeOrderByDatetimeDesc(Employee employee, Pageable pageable);
    ApplyHistory findByEmployeeAndRecruitment(Employee employee, Recruitment recruitment);
}
