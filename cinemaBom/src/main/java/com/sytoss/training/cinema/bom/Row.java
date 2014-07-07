package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.List;

import bom.exception.NullObjectInsertionException;

public class Row {

  private int number;

  private List<Place> places;

  private Room room;

  public Room getRoom() {
    return room;
  }

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
    if (exists(place)) {
      throw new IllegalArgumentException("Place with number " + place.getNumber() + " already exists.");
    }
    places.add(place);
    place.setRow(this);

  }

  public boolean exists(Place searchedPlace) {
    for (Place place : places) {
      if (place.getNumber() == searchedPlace.getNumber()) {
        return true;
      }
    }
    return false;
  }

  public void setRoom(Room room) {
    if ( !room.exists(this)) {

      room.addRow(this);

    }
    this.room = room;
  }

}
