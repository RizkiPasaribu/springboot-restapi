package com.example.demo.student;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
  
  @Bean
  CommandLineRunner initDatabase (StudentRepository studentRepository){
    return args->{
      studentRepository.save(new Student("Rizki","innet71050500@gmail.com","Asrama Polisi", LocalDate.of(2000, 5, 5)));
      studentRepository.save(new Student("Gusmardi","cndcpt1@gmail.com","Madina",LocalDate.of(2001, 5, 5)));
    };
  }
}
