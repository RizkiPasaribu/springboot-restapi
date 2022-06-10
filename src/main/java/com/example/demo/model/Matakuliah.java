package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_matakuliah")
public class Matakuliah {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  private String Matkul;

  @ManyToMany(mappedBy = "matakuliah", fetch = FetchType.EAGER)
  Set<Student> student;

  public Matakuliah() {
  }
  
  public Matakuliah(String Matkul) {
    this.Matkul = Matkul;
  }

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((Id == null) ? 0 : Id.hashCode());
    result = prime * result + ((Matkul == null) ? 0 : Matkul.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Matakuliah other = (Matakuliah) obj;
    if (Id == null) {
      if (other.Id != null)
        return false;
    } else if (!Id.equals(other.Id))
      return false;
    if (Matkul == null) {
      if (other.Matkul != null)
        return false;
    } else if (!Matkul.equals(other.Matkul))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Matakuliah [Id=" + Id + ", Matkul=" + Matkul + "]";
  }
}
