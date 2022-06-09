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
import com.example.demo.model.Matakuliah;
import com.example.demo.services.MatakuliahService;

@RestController
@RequestMapping("/matakuliah")
public class MatakuliahController {
  MatakuliahService matakuliahService;
  
  MatakuliahController(MatakuliahService matakuliahService){
    this.matakuliahService = matakuliahService;
  }

   // get all matakuliah
  @GetMapping()
  public List<Matakuliah> all(){
    return matakuliahService.getAll();
  }

  // add Matakuliah
  @PostMapping()
  public ResponseEntity<ResponseData<Matakuliah>> add(@RequestBody Matakuliah dosen){
    ResponseData<Matakuliah> responseData = new ResponseData<>();

    responseData.setStatus(true);
    responseData.getMessages().add("Data Berhasil Ditambah");
    responseData.setPayload(matakuliahService.addOne(dosen));
    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

   // Find matkul By Id
  @GetMapping("{id}")
  public ResponseEntity<ResponseData<Matakuliah>> findMatkul(@PathVariable Long id){
    ResponseData<Matakuliah> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("Data Ditemukan");
    responseData.setPayload(matakuliahService.findOne(id));
    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

  // Delete matakuliah
  @DeleteMapping("{id}")
  public ResponseEntity<ResponseData<Matakuliah>> deleteDosen(@PathVariable Long id){
    matakuliahService.deleteOne(id);
    
    ResponseData<Matakuliah> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("Data Berhasil Di hapus");
    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

   // Edit Dosen
   @PutMapping("/{id}")
   public ResponseEntity<ResponseData<Matakuliah>> add(@RequestBody Matakuliah dosen, @PathVariable Long id){
     ResponseData<Matakuliah> responseData = new ResponseData<>();
     responseData.setStatus(true);
     responseData.getMessages().add("Data Berhasil Diubah");
     responseData.setPayload(matakuliahService.editMatkul(dosen,id));
 
     return ResponseEntity.status(HttpStatus.OK).body(responseData);
   }
}
