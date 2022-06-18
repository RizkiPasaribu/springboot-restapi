package com.example.demo.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.CostomException;
import com.example.demo.model.Users;
import com.example.demo.repos.UsersRepository;

@Service
public class UserService {
  private final UsersRepository usersRepository;
  private PasswordEncoder encoder;
  
  public UserService(UsersRepository usersRepository,PasswordEncoder encoder){
    this.usersRepository = usersRepository;
    this.encoder = encoder;
  }

  public Users register(Users users){
    Users user = usersRepository.findByUsername(users.getUsername());
    if(user == null){
      Users newUser = new Users();
      newUser.setUsername(users.getUsername());
      newUser.setPassword(encoder.encode(users.getPassword()));
      newUser.setRoles(users.getRoles());
      return usersRepository.save(newUser);
    }
    throw new CostomException("Username has been taken");
  }
}
