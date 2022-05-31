package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    Optional <Student> studentOptional = studentRepository.findStudentByNama(student.getEmail());
    if(studentOptional.isPresent()) throw new IllegalStateException("Email Taken");
    return studentRepository.save(student);
  }

  public Student findStudent(Long id){
    return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException(id));
  }

  @Transactional
  public Student editStudent(Student student, Long id){
    return studentRepository.findById(id)
      .map(studentt -> {
        studentt.setNama(student.getNama());
        studentt.setEmail(student.getEmail());
        studentt.setAlamat(student.getAlamat());
        studentt.setDob(student.getDob());
        return studentRepository.save(studentt);
      })
      .orElseThrow(()->new StudentNotFoundException(id));
  }

  public void deleteOne(Long id){
    boolean exist = studentRepository.existsById(id);
    if(!exist) throw new StudentNotFoundException(id);
    studentRepository.deleteById(id);
  }
}
