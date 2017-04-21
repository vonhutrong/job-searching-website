package com.trong.repository;

import com.trong.model.Employer;
import com.trong.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecruitmentRepository extends PagingAndSortingRepository<Recruitment, Long> {
    List<Recruitment> findAllByOrderByCreatedDatetimeAsc();
    Recruitment findById(Long id);
    List<Recruitment> findByTitleContainingIgnoreCaseAndDepartmentIdOrderByCreatedDatetimeAsc(String keyword, Long departmentId);
    List<Recruitment> findByEmployerOrderByCreatedDatetimeAsc(Employer employer);

    Page<Recruitment> findAllByOrderByCreatedDatetimeAsc(Pageable pageable);
}
