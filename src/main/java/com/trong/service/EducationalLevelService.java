package com.trong.service;

import com.trong.model.EducationalLevel;

import java.util.List;

public interface EducationalLevelService {
    EducationalLevel findById(Long id);
    List<EducationalLevel> findAll();
}
