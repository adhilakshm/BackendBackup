package com.example.demo.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Login;

public interface LoginRepo extends JpaRepository<Login, Integer> {
	 Login findByUsername(String username);

	Optional<Login> findById(Long id);

}
