package com.trong.service;

import com.trong.model.Recruitment;

import java.util.List;

public interface RecruitmentService {
    void save(Recruitment recruitment);
    List<Recruitment> recent();
    Recruitment findById(Long id);
    List<Recruitment> searchBasic(String keyword, Long departmentId);
}
