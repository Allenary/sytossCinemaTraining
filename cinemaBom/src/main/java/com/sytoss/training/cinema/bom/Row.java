package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.List;

import bom.exception.NullObjectInsertionException;

public class Row {

  private int number;

  private List<Place> places;

  public Row(int number) {
    this();
    this.number = number;
  }

  public Row() {
    places = new ArrayList<Place>();
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    if (number <= 0) {
      throw new IllegalArgumentException("Number should be bigger than zero.");
    }
    this.number = number;
  }

  public void addPlace(Place place) {
    if (place == null) {
      throw new NullObjectInsertionException("Place couldn't be null");
    }
    places.add(place);
    place.setRow(this);
  }

  public boolean exists(Place searchedPlace) {
    for (Place place : places) {
      if (place.equals(searchedPlace)) {
        return true;
      }
    }
    return false;
  }

}
