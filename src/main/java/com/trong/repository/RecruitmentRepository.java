package com.trong.repository;

import com.trong.model.Employer;
import com.trong.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecruitmentRepository extends PagingAndSortingRepository<Recruitment, Long> {
    Recruitment findById(Long id);
    Page<Recruitment> findByBannedIsNullOrBannedIsFalseOrderByCreatedDatetimeDesc(Pageable pageable);
    Page<Recruitment> findByEmployerOrderByCreatedDatetimeAsc(Employer employer, Pageable pageable);
}
