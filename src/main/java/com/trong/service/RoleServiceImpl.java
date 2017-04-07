package com.trong.service;

import com.trong.model.Role;
import com.trong.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findFirstByName(String name) {
        return roleRepository.findFirstByName(name);
    }
}
