package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseData;
import com.example.demo.model.Dosenpem;
import com.example.demo.services.DosenpemService;

@RestController
@RequestMapping("/dosen")
public class DosenController {
  private final DosenpemService dosenService;

  DosenController(DosenpemService dosenpemService){
    this.dosenService = dosenpemService;
  }

  // get all Dosen
  @GetMapping()
  public List<Dosenpem> all(){
    return dosenService.getAll();
  }

  // add Dosen
  @PostMapping()
  public ResponseEntity<ResponseData<Dosenpem>> add(@RequestBody Dosenpem dosen){
    ResponseData<Dosenpem> responseData = new ResponseData<>();

    responseData.setStatus(true);
    responseData.getMessages().add("Data Berhasil Ditambah");
    responseData.setPayload(dosenService.addOne(dosen));
    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

  // Find Dosen By Id
  @GetMapping("{id}")
  public ResponseEntity<ResponseData<Dosenpem>> findDosen(@PathVariable Long id){
    ResponseData<Dosenpem> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("Data Ditemukan");
    responseData.setPayload(dosenService.findOne(id));

    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

  // Delete Dosen
  @DeleteMapping("{id}")
  public ResponseEntity<ResponseData<Dosenpem>> deleteDosen(@PathVariable Long id){
    dosenService.deleteOne(id);
    
    ResponseData<Dosenpem> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("Data Berhasil Di hapus");
    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

  // Edit Dosen
  @PutMapping("/{id}")
  public ResponseEntity<ResponseData<Dosenpem>> add(@RequestBody Dosenpem dosen, @PathVariable Long id){
    ResponseData<Dosenpem> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("Data Berhasil Diubah");
    responseData.setPayload(dosenService.editDosen(dosen,id));

    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }
}
