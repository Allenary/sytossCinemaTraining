package com.sytoss.training.cinema.bom;

import bom.exception.NullObjectInsertionException;

public class Place {

  private int number;

  private Row row;

  public Place() {
  }

  public Place(int number) {
    this.number = number;

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

  public Row getRow() {
    return row;
  }

  public void setRow(Row row) {
    if (row == null) {
      throw new NullObjectInsertionException("Null row cannot be added");
    }
    this.row = row;
    if ( !row.exists(this)) {
      row.addPlace(this);
    }

  }

}
