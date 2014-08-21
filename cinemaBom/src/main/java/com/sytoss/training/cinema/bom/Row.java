package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sytoss.training.cinema.exception.DuplicateInsertionException;
import com.sytoss.training.cinema.exception.NullObjectInsertionException;
import com.sytoss.training.cinema.exception.ReassignObjectException;

public class Row {

  private int number;

  private List<Place> places;

  private Room room;

  public Row(int number) {
    places = new ArrayList<Place>();
    setNumber(number);
  }

  public Row(int number, Room anotherRoom) {
    this(number);
    setRoom(anotherRoom);
  }

  public Room getRoom() {
    return room;
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
      throw new DuplicateInsertionException("Place with number " + place.getNumber() + " already exists.");
    }
    if (place.getRow() != null) {
      throw new ReassignObjectException("This Place already exists in another row. Cannot be reassigned");
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
    if (room == null) {
      throw new IllegalArgumentException();
    }
    if (this.room != null && !this.room.equals(room)) {
      throw new ReassignObjectException();
    }

    if ( !room.exists(this)) {
      room.addRow(this);
    }
    this.room = room;
  }

  public Iterator<Place> getAllPlaces() {
    return places.iterator();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other == this)
      return true;
    if (this.room == null)
      return false;
    Row otherRow = (Row) other;
    return this.number == otherRow.getNumber() && this.room.equals(otherRow.getRoom());
  }

  public Place registerPlace(Place place) {
    int index = places.indexOf(place);
    if (index == -1) {
      addPlace(place);
      return place;
    }
    return places.get(index);
  }
}
