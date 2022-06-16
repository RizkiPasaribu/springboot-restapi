package com.example.demo.repos;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Matakuliah;
import com.example.demo.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// anotasi repository bertuan untuk menandakan bahwaini adalah DAO layer
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findStudentByEmail(String email);

  // custom query untuk lebih detail liat di spring data jpa(jpql) docs
  @Query("SELECT p FROM Student p WHERE :matkul MEMBER OF p.matakuliah")
  List<Student> findStudetByMatakuliah(@Param("matkul") Matakuliah matakuliah);
}