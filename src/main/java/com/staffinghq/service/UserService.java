package com.staffinghq.service;

import com.staffinghq.entity.User;
import com.staffinghq.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User getById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}