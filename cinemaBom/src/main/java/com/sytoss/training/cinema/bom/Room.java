package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bom.exception.DuplicateInsertionException;
import bom.exception.NullObjectInsertionException;
import bom.exception.ReassignObjectException;

public class Room {

  private List<Row> rows;

  private String name;

  public Room() {
    rows = new ArrayList<Row>();
  }

  public Room(String name) {
    this();
    this.name = name;
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
    if (exists(row)) {
      throw new DuplicateInsertionException("this row already added to the room. cannot be added second time");
    }
    if (row.getRoom() != null && row.getRoom() != this) {
      throw new ReassignObjectException("This row already assigned to another room");
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

}
