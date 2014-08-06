package com.sytoss.training.cinema.exception;

public class SeanceNotFullException extends CinemaException {

  public SeanceNotFullException(String message) {
    super(message);
  }

  public SeanceNotFullException(String message, Exception e) {
    super(message + e.getStackTrace());
  }

}
