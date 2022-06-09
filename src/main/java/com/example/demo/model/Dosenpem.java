package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "tbl_dosen")
public class Dosenpem {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
  
	private String nama;
	private String alamat;

  public Dosenpem() {
  }

  public Dosenpem(String nama, String alamat) {
    this.nama = nama;
    this.alamat = alamat;
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
    Dosenpem other = (Dosenpem) obj;
    if (alamat == null) {
      if (other.alamat != null)
        return false;
    } else if (!alamat.equals(other.alamat))
      return false;
    if (nama == null) {
      if (other.nama != null)
        return false;
    } else if (!nama.equals(other.nama))
      return false;
    return true;
  } 
}

