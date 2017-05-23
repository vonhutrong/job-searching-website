package com.trong.service;

import com.trong.form.AdvancedSearchForm;
import com.trong.model.Employer;
import com.trong.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {
    void save(Recruitment recruitment);
    Recruitment findById(Long id);

    Page<Recruitment> recent(Pageable pageable);
    Page<Recruitment> searchBasic(String keyword, Long departmentId, Pageable pageable);

    Page<Recruitment> findByEmployer(Employer employer, Pageable pageable);

    Page<Recruitment> search(AdvancedSearchForm advancedSearchForm, Pageable pageable);

    Page<Recruitment> getTopReportedRecruitments(Pageable pageable);

    Page<Recruitment> findByEmployerNotBanned(Employer employer, Pageable pageable);
}
