package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Matakuliah;

public interface MatakuliahRepository extends JpaRepository<Matakuliah, Long> {
  
}
