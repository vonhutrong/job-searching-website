package com.trong.service;

import com.trong.model.User;

public interface UserService {
    void save(User user);
    User findByEmail(String username);
}
