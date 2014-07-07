package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.List;

import bom.exception.NullObjectInsertionException;

public class Room {

  List<Row> rows;

  public Room() {
    rows = new ArrayList<Row>();
  }

  private String name;

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
    rows.add(row);
    row.setRoom(this);

  }

  public boolean exists(Row row) {
    return rows.contains(row);
  }
}