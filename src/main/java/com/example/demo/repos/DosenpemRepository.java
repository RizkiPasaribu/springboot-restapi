package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Dosenpem;

public interface DosenpemRepository extends JpaRepository<Dosenpem, Long> {
  
}
