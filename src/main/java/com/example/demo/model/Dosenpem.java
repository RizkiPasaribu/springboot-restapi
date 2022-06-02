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
  @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
  
	private String nama;
	private String alamat;

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
}
