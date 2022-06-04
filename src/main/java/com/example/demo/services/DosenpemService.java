package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.NotFoundExceptionById;
import com.example.demo.model.Dosenpem;
import com.example.demo.repos.DosenpemRepository;

@Service
public class DosenpemService {
  private final DosenpemRepository dosenRepo;
  
  public DosenpemService(DosenpemRepository dosenpemRepository){
    this.dosenRepo = dosenpemRepository;
  }

  public List<Dosenpem> getAll(){
    return dosenRepo.findAll();
  }

  public Dosenpem addOne(Dosenpem dosen){
    return dosenRepo.save(dosen);
  }

  public Dosenpem findOne(Long id){
    return dosenRepo.findById(id).orElseThrow(()->new NotFoundExceptionById(id, "Dosen"));
  }

  public void deleteOne(Long id){
    boolean exist = dosenRepo.existsById(id);
    if(!exist) throw new NotFoundExceptionById(id, "Dosen");
    dosenRepo.deleteById(id);
  }
}
