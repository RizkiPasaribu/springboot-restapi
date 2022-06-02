package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_matakuliah")
public class Matakuliah {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long Id;
  private String Matkul;

  @ManyToMany(mappedBy = "matakuliah")
  Set<Student> student;

  public Long getId() {
    return Id;
  }
  public void setId(Long id) {
    Id = id;
  }
  public String getMatkul() {
    return Matkul;
  }
  public void setMatkul(String matkul) {
    Matkul = matkul;
  }
}
