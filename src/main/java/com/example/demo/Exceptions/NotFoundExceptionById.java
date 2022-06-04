package com.example.demo.Exceptions;

public class NotFoundExceptionById extends RuntimeException{
  public NotFoundExceptionById(Long id, String message) {
    super("Could not find "+ message +" with "+ id);
  }
}
