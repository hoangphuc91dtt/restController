package com.example.buoi1.service;

import com.example.buoi1.model.UserDemo;
import com.example.buoi1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveOrUpdate(UserDemo user) {
        userRepository.save(user);
    }

    public List<UserDemo> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDemo getUserById(int id) {
        Optional<UserDemo> user = userRepository.findById(id);
        return user.orElse(null);  // Trả về null nếu không tìm thấy user
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
