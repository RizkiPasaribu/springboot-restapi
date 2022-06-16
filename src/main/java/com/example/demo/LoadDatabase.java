package com.example.demo;

import java.time.LocalDate;
import java.util.Set;

import com.example.demo.model.Dosenpem;
import com.example.demo.model.Matakuliah;
import com.example.demo.model.Student;
import com.example.demo.model.Users;
import com.example.demo.repos.DosenpemRepository;
import com.example.demo.repos.MatakuliahRepository;
import com.example.demo.repos.StudentRepository;
import com.example.demo.repos.UsersRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
  
  @Bean
  CommandLineRunner initDatabase (StudentRepository studentRepository, DosenpemRepository dosenpemRepository, MatakuliahRepository matakuliahRepository, UsersRepository usersRepository){
    return args->{
      
      usersRepository.save(new Users("rizki","rizki", "ADMIN"));
      usersRepository.save(new Users("rizki1","rizki1", "USER"));

      Matakuliah matakuliah = new Matakuliah("Artifcial Intelegen");
      matakuliahRepository.save(matakuliah);

      Dosenpem dosen = new Dosenpem("Julianto", "Medan Ringroot");
      dosenpemRepository.save(dosen);

      studentRepository.save(new Student("Rizki","innet71050500@gmail.com","Asrama Polisi", LocalDate.of(2000, 5, 5),dosen, Set.of(matakuliah)));
      studentRepository.save(new Student("Gusmardi","cndcpt1@gmail.com","Madina",LocalDate.of(2001, 5, 5),dosen, Set.of(matakuliah)));
    };
  }
}
