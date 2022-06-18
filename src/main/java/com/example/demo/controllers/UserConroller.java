package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseData;
import com.example.demo.model.Users;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/user")
public class UserConroller {
  UserService userService;

  UserConroller(UserService userService){
    this.userService = userService;
  }


  @GetMapping
  public String tes(@RequestBody Users user){
    System.out.println(user.getUsername());
    return "Bisa";
  }
  
  @PostMapping
  public ResponseEntity<ResponseData<Users>> registerUser(@RequestBody Users user) {
    ResponseData<Users> responseData = new ResponseData<>();
    System.out.print("ok");
    responseData.getMessages().add("Data has been added");
    responseData.setStatus(true);
    responseData.setPayload(userService.register(user));
    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }
}
