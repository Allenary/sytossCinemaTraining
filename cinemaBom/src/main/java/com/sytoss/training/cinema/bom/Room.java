package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sytoss.training.cinema.exception.NullObjectInsertionException;
import com.sytoss.training.cinema.exception.ReassignObjectException;

public class Room {

  private List<Row> rows;

  private String name;

  public Room(String name) {
    setName(name);
    rows = new ArrayList<Row>();
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name shouldn't be NULL or empty.");
    }
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void addRow(Row row) {
    if (row == null) {
      throw new NullObjectInsertionException();
    }

    //    if (row.getRoom() != null && row.getRoom().equals(this)) {
    //      throw new ReassignObjectException("This row already assigned to another room");
    //    }

    if (exists(row)) {
      throw new ReassignObjectException();
    }
    rows.add(row);
    row.setRoom(this);
  }

  public boolean exists(Row row) {
    return rows.contains(row) || hasRowWithNumber(row.getNumber());
  }

  private boolean hasRowWithNumber(int number) {
    for (Row row : rows) {
      if (row.getNumber() == number) {
        return true;
      }
    }
    return false;
  }

  public Iterator<Row> getAllRows() {
    return rows.iterator();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other == this)
      return true;
    Room otherRoom = (Room) other;
    return this.name.equals(otherRoom.name);
  }

  public Row registerRow(Row row) {
    int index = rows.indexOf(row);
    if (index == -1) {
      addRow(row);
      return row;
    } else {
      Row foundedRow = rows.get(index);
      Iterator<Place> places = row.getAllPlaces();
      while (places.hasNext()) {
        foundedRow.registerPlace(places.next());
      }
      return foundedRow;
    }
  }

}
