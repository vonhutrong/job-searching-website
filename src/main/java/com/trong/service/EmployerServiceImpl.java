package com.trong.service;

import com.trong.model.Employer;
import com.trong.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerServiceImpl implements EmployerService {
    @Autowired
    private UserService userService;

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public void save(Employer employer) {
        employerRepository.save(employer);
    }

    @Override
    public Employer findByEmail(String email) {
        return employerRepository.findByUser(userService.findByEmail(email));
    }
}
