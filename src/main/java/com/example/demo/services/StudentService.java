package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Exceptions.CostomException;
import com.example.demo.model.Student;
import com.example.demo.repos.StudentRepository;


// anotasi @Service berguna untuk memberitahu bahwa class StudentService ini adalah Service sehingga dapat di autowired oleh Student controller
// selain anotasi @Service ada juga Anotasi Component,
@Service
public class StudentService {
  private final StudentRepository studentRepository;

  // dependency injeksi dengan constructor
  public StudentService(StudentRepository studentRepository){
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudent(){
    return studentRepository.findAll();
  }

  public Student addStudent(Student student){
    Optional <Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if(studentOptional.isPresent()) throw new CostomException("Email Taken");
    return studentRepository.save(student);
  }

  public Student findStudent(Long id){
    return studentRepository.findById(id).orElseThrow(()->new CostomException(id, "Student"));
  }

  @Transactional
  public Student editStudent(Student student, Long id){
    return studentRepository.findById(id)
      .map(studentt -> {
        studentt.setNama(student.getNama());
        studentt.setEmail(student.getEmail());
        studentt.setAlamat(student.getAlamat());
        studentt.setDob(student.getDob());
        studentt.setDosen_pembimbing(student.getDosen_pembimbing());
        studentt.setMatakuliah(student.getMatakuliah());
        return studentRepository.save(studentt);
      })
      .orElseThrow(()->new CostomException(id, "Student"));
  }

  public void deleteOne(Long id){
    boolean exist = studentRepository.existsById(id);
    if(!exist) throw new CostomException(id, "Student");
    studentRepository.deleteById(id);
  }
}
