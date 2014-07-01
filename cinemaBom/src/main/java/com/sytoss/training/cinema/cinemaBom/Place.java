package com.sytoss.training.cinema.cinemaBom;

import java.util.ArrayList;
import java.util.Arrays;

public class Place {

  int number;

  String status;

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    if (number <= 0) {
      throw new IllegalArgumentException();
    }
    this.number = number;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    ArrayList possibleStatus = new ArrayList(Arrays.asList("available", "unavailable"));
    if (status == null || status == "" || !possibleStatus.contains(status.toLowerCase())) {
      throw new IllegalArgumentException();
    } else {
      this.status = status;
    }

  }

}
