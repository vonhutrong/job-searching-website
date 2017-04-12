package com.trong.service;

import com.trong.model.EducationalLevel;
import com.trong.repository.EducationalLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationalLevelServiceImpl implements EducationalLevelService {
    @Autowired
    private EducationalLevelRepository educationalLevelRepository;

    @Override
    public EducationalLevel findById(Long id) {
        return educationalLevelRepository.findById(id);
    }

    @Override
    public List<EducationalLevel> findAll() {
        return educationalLevelRepository.findAll();
    }
}
