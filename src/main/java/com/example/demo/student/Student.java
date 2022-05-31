package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity // This tells Hibernate to make a table out of this class
public class Student {
  
  // anotasi @id menandakam primari key adalah colom id dan anotasi generatedvalue menandakan id akan di generate secara ber urutan 
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
  
  @NotEmpty(message = "nama is required")
	private String nama;

  @NotEmpty(message = "email is required")
	private String email;
  
  @NotEmpty(message = "alamat is required")
	private String alamat;

  @NotNull(message = "DOB Is Required")
  private LocalDate dob;

  // Anotasi Transient ini berguna agar data tidak disimpan di database
  @Transient
  private Integer age;


  public Student() {
  }

  public Student(String nama, String email, String alamat, LocalDate dob) {
    this.nama = nama;
    this.email = email;
    this.alamat = alamat;
    this.dob = dob;
  }
  
  public Student(Long id, String nama, String email, String alamat, LocalDate dob) {
    this.id = id;
    this.nama = nama;
    this.email = email;
    this.alamat = alamat;
    this.dob = dob;
  } 

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public Integer getAge() {
    return Period.between(this.dob, LocalDate.now()).getYears();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getAlamat() {
    return alamat;
  }
  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((alamat == null) ? 0 : alamat.hashCode());
    result = prime * result + ((dob == null) ? 0 : dob.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((nama == null) ? 0 : nama.hashCode());
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
    Student other = (Student) obj;
    if (alamat == null) {
      if (other.alamat != null)
        return false;
    } else if (!alamat.equals(other.alamat))
      return false;
    if (dob == null) {
      if (other.dob != null)
        return false;
    } else if (!dob.equals(other.dob))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (nama == null) {
      if (other.nama != null)
        return false;
    } else if (!nama.equals(other.nama))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Student [age=" + age + ", alamat=" + alamat + ", dob=" + dob + ", email=" + email + ", id=" + id + ", nama="
        + nama + "]";
  }
}
