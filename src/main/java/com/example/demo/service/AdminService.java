package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;
import com.example.demo.repo.LoginRepo;

@Service
public class AdminService {

    @Autowired
    private LoginRepo repo;

    public boolean updatePassword(String username, String newPassword) {
        Optional<Login> optionalAdmin = Optional.of(repo.findByUsername(username));

        if (optionalAdmin.isPresent()) {
            Login admin = optionalAdmin.get();
            admin.setPassword(newPassword);
            repo.save(admin);
            return true;
        }

        return false;
    }


   
}
