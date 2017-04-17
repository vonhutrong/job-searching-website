package com.trong.repository;

import com.trong.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findAllByOrderByCreatedDatetimeAsc();
    Recruitment findById(Long id);
    List<Recruitment> findByTitleContainingAndDepartmentIdOrderByCreatedDatetimeAsc(String keyword, Long departmentId);
}
