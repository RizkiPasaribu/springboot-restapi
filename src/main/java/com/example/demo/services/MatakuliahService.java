package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.NotFoundExceptionById;
import com.example.demo.model.Matakuliah;
import com.example.demo.repos.MatakuliahRepository;

@Service
public class MatakuliahService {
  private final MatakuliahRepository matakuliahRepo;

  public MatakuliahService(MatakuliahRepository matakuliahRepository){
    this.matakuliahRepo = matakuliahRepository;
  }

  public List<Matakuliah> getAll(){
    return matakuliahRepo.findAll();
  }

  public Matakuliah addOne(Matakuliah matakuliah){
    return matakuliahRepo.save(matakuliah);
  }

  public Matakuliah findOne(Long id){
    return matakuliahRepo.findById(id).orElseThrow(()->new NotFoundExceptionById(id, "Matakuliah"));
  }

  public void deleteOne(Long id){
    boolean exist = matakuliahRepo.existsById(id);
    if(!exist) throw new NotFoundExceptionById(id, "Matakuliah");
    matakuliahRepo.deleteById(id);
  }
}
