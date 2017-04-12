package com.trong.service;

import com.trong.model.Employer;

public interface EmployerService {
    void save(Employer employer);
    Employer findByEmail(String name);
}
