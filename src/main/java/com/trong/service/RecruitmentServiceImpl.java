package com.trong.service;

import com.trong.model.Employer;
import com.trong.model.Recruitment;
import com.trong.repository.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Recruitment> recent() {
        return recruitmentRepository.findAllByOrderByCreatedDatetimeAsc();
    }

    @Override
    public Recruitment findById(Long id) {
        return recruitmentRepository.findById(id);
    }

    @Override
    public List<Recruitment> searchBasic(String keyword, Long departmentId) {
        return recruitmentRepository.findByTitleContainingIgnoreCaseAndDepartmentIdOrderByCreatedDatetimeAsc(keyword, departmentId);
    }

    @Override
    public List<Recruitment> findByEmployer(Employer employer) {
        return recruitmentRepository.findByEmployerOrderByCreatedDatetimeAsc(employer);
    }

    @Override
    public Page<Recruitment> recent(Pageable pageable) {
        return recruitmentRepository.findAllByOrderByCreatedDatetimeAsc(pageable);
    }
}
