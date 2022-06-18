package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;


import com.example.demo.model.Users;
import com.example.demo.repos.UsersRepository;

public class CustomUserDetailsService implements UserDetailsService{
  @Autowired
  UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user = usersRepository.findByUsername(username);
    UserBuilder builder = null;
    if (user != null) {
      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.password(user.getPassword());
      builder.roles(user.getRoles());
    } else {
      throw new UsernameNotFoundException("User not found.");
    }
    return builder.build();
  }
}

// disini saya menggunakan user builder karna saya main cepat
// bisa saja user builder nya kita custom jadi nanti kitabuat class baru yang implements ke userdetail
// sebebar nya cara nya sama saja cuma beda nya klw nanti kita buat class baru lebih enak
// karna bisa kita custom sendiri ini ref nya klw mw make yang cutom
// https://www.baeldung.com/spring-security-authentication-with-a-database