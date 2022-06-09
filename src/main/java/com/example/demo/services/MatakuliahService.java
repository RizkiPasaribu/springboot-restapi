package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.CostomException;
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
    return matakuliahRepo.findById(id).orElseThrow(()->new CostomException(id, "Matakuliah"));
  }

  public void deleteOne(Long id){
    boolean exist = matakuliahRepo.existsById(id);
    if(!exist) throw new CostomException(id, "Matakuliah");
    matakuliahRepo.deleteById(id);
  }

  @Transactional
  public Matakuliah editMatkul(Matakuliah matkul, Long id){
    return matakuliahRepo.findById(id)
      .map(matkull -> {
        matkull.setMatkul(matkul.getMatkul());
        return matakuliahRepo.save(matkull);
      })
      .orElseThrow(()->new CostomException(id, "Matakuliah"));
  }
}
