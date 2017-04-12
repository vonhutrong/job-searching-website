package com.trong.service;

import com.trong.model.Department;

import java.util.List;

public interface DepartmentService {
    Department findById(Long id);
    List<Department> findAll();
}
