package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.dto.ResponseData;
import com.example.demo.hateoas.StudentModelAssembler;
import com.example.demo.model.Student;
import com.example.demo.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


// anotasi requets mapping bertujuan agar mengelompokan routing
@RestController
@RequestMapping("/student")
public class StudentController {
  private final StudentService studentService;
  private final StudentModelAssembler studentModelAssembler;

  // dependency injection cosntructor
  // sehingga Object dari StudentService langsung di suntikkan ke dalam constructor StudentController ini
  // anotasi @autowired berguna untuk memberitahu bahaw kita memakai constructor ini untuk dependensi injeksion
  // karna nanti mungkin saja kita memakai lebih dari satu constructor materi ini ada di yt programer jaman now
  @Autowired
  public StudentController(StudentService studentService, StudentModelAssembler studentModelAssembler) {
    this.studentService = studentService;
    this.studentModelAssembler = studentModelAssembler;
  }

  // get all student 
  @GetMapping()
  public CollectionModel<EntityModel<Student>> all() {
    List<EntityModel<Student>> students = studentService.getStudent().stream()
      .map(studentModelAssembler::toModel).collect(Collectors.toList());
    return CollectionModel.of(students, linkTo(methodOn(StudentController.class).all()).withSelfRel());
  }

  // get student by id
  @GetMapping("/{id}")
  public EntityModel<Student> one(@PathVariable Long id) {
    Student student = studentService.findStudent(id);
    return studentModelAssembler.toModel(student);
  }

  // edit student
  @PutMapping("/{id}")
  public ResponseEntity<ResponseData<Student>> edit(@Validated @RequestBody Student student, @PathVariable Long id, Errors errors ) {
    ResponseData<Student> responseData = new ResponseData<Student>();

    if (errors.hasErrors()){
      for (ObjectError error: errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    
    responseData.getMessages().add("Data has been changed");
    responseData.setStatus(true);
    responseData.setPayload(studentService.editStudent(student, id));
    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

  // delete student by id
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteOne(@PathVariable Long id) {
    studentService.deleteOne(id);
    return ResponseEntity.status(HttpStatus.OK).body("Data Has Been Deleted"); 
  }

  // add new student if email not exist
  @PostMapping("/add")
  public ResponseEntity<ResponseData<Student>> addNewStudent(@Valid @RequestBody Student newStudent, Errors errors) {

    ResponseData<Student> responseData = new ResponseData<Student>();

    if (errors.hasErrors()){
      for (ObjectError error: errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    responseData.getMessages().add("Data has been added");
    responseData.setStatus(true);
    responseData.setPayload(studentService.addStudent(newStudent));
    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

  @GetMapping("/search/matkul/{id}")
  public List<Student> searchStudentByMatkul(@PathVariable Long id) {
    return studentService.findMatakuliah(id);
  } 
}