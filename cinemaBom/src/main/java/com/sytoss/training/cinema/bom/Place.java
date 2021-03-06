package com.sytoss.training.cinema.bom;

import com.sytoss.training.cinema.exception.NullObjectInsertionException;
import com.sytoss.training.cinema.exception.ReassignObjectException;

public class Place {

  private int number;

  private Row row;

  public Place(int number) {
    setNumber(number);
  }

  public Place(int number, Row row) {
    this(number);
    setRow(row);
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
    if (this.row != null && !this.row.equals(row)) {
      throw new ReassignObjectException("This Place already exists in another row. Cannot be reassigned");
    }

    if ( !row.exists(this)) {
      row.addPlace(this);
    }

    this.row = row;

  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other == this)
      return true;
    if (this.row == null)
      return false;
    Place otherPlace = (Place) other;
    return this.number == otherPlace.getNumber() && this.row.equals(otherPlace.getRow());
  }
}
