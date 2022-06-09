package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.CostomException;
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
    return dosenRepo.findById(id).orElseThrow(()->new CostomException(id, "Dosen"));
  }

  @Transactional
  public Dosenpem editDosen(Dosenpem dosen, Long id){
    return dosenRepo.findById(id)
      .map(dosenn -> {
        dosenn.setAlamat(dosen.getAlamat());
        dosenn.setNama(dosen.getNama());
        return dosenRepo.save(dosenn);
      })
      .orElseThrow(()->new CostomException(id, "Dosen"));
  }

  public void deleteOne(Long id){
    boolean exist = dosenRepo.existsById(id);
    if(!exist) throw new CostomException(id, "Dosen");
    dosenRepo.deleteById(id);
  }
}
