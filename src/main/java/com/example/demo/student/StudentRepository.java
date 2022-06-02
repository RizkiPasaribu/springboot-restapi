package com.example.demo.student;

import java.util.Optional;

import com.example.demo.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// anotasi repository bertuan untuk menandakan bahwaini adalah DAO layer
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findStudentByEmail(String email);
}

