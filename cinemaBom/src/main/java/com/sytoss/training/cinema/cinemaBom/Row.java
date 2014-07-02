package com.sytoss.training.cinema.cinemaBom;

import java.util.List;

public class Row {

  private PlacesStatuses status;

  private int number;

  private List<Place> places;

  public PlacesStatuses getStatus() {
    return status;
  }

  public void setStatus(PlacesStatuses status) {
    if(status==null) {
      throw new NullPointerException();
    }
    this.status = status;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    if (number <= 0) {
      throw new IllegalArgumentException();
    }
    this.number = number;
  }

  public List<Place> getPlaces() {
    return places;
  }

  public void setPlaces(List<Place> places) {
    this.places = places;
  }

}
