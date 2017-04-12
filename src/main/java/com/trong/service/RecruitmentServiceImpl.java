package com.trong.service;

import com.trong.model.Recruitment;
import com.trong.repository.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Override
    public void save(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }
}
