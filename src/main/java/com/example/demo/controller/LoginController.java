package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Login;
import com.example.demo.repo.LoginRepo;

@RestController
public class LoginController {
    @Autowired
    private LoginRepo repo;
    @CrossOrigin(origins = "http://localhost:4200") 
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Login login = repo.findByUsername(username);

        if (login == null || !password.equals(login.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().body("{\"message\": \"Authentication successful\"}");
    }
   
    
    
    
    
    
    
    
    

@CrossOrigin(origins = "http://localhost:4200")
@PutMapping("/updatePassword/{id}")
public ResponseEntity<String> updatePassword(@PathVariable Long id, @RequestBody Map<String, String> newPasswordObj) {
    Optional<Login> optionalLogin = repo.findById(id);

    if (optionalLogin.isPresent()) {
        Login login = optionalLogin.get();
        String newPassword = newPasswordObj.get("newPassword");
        login.setPassword(newPassword);
        repo.save(login);
        return ResponseEntity.ok().body("Password updated successfully.");
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
}
}
