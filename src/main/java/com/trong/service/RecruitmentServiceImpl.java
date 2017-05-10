package com.trong.service;

import com.trong.model.Employer;
import com.trong.model.Recruitment;
import com.trong.repository.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;

    @Autowired
    public RecruitmentServiceImpl(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    public void save(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

    @Override
    public Recruitment findById(Long id) {
        return recruitmentRepository.findById(id);
    }

    @Override
    public Page<Recruitment> recent(Pageable pageable) {
        return recruitmentRepository.findAllByOrderByCreatedDatetimeDesc(pageable);
    }

    @Override
    public Page<Recruitment> searchBasic(String keyword, Long departmentId, Pageable pageable) {
        return recruitmentRepository.findByTitleContainingIgnoreCaseAndDepartmentIdOrderByCreatedDatetimeAsc(keyword, departmentId, pageable);
    }

    @Override
    public Page<Recruitment> findByEmployer(Employer employer, Pageable pageable) {
        return recruitmentRepository.findByEmployerOrderByCreatedDatetimeAsc(employer, pageable);
    }
}
