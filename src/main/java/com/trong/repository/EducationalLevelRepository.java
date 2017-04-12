package com.trong.repository;

import com.trong.model.EducationalLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationalLevelRepository extends JpaRepository<EducationalLevel, Long> {
    EducationalLevel findById(Long id);
}
