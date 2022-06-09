package com.example.demo.Exceptions;

public class CostomException extends RuntimeException{
  public CostomException(Long id, String message) {
    super("Could not find "+ message +" with "+ id);
  }

  public CostomException(String message) {
    super("Email Taken");
  }
}
