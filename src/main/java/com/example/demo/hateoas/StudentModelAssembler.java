package com.example.demo.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.demo.controllers.StudentController;
import com.example.demo.model.Student;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class StudentModelAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {
  @Override
  public EntityModel<Student> toModel (Student student){
    return EntityModel.of(student,
    linkTo(methodOn(StudentController.class).one(student.getId())).withSelfRel(),
    linkTo(methodOn(StudentController.class).all()).withRel("all students"));
  }
}
