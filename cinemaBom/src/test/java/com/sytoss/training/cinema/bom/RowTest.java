package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import bom.exception.NullObjectInsertionException;

public class RowTest {

  @Test
  public void shouldSetValidRoom() {
    Row row = new Row();
    Room room = new Room();
    row.setRoom(room);
    assertEquals(room, row.getRoom());
    assertTrue(room.exists(row));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseErrorForSetNullRoom() {
    new Row().setRoom(null);
  }

  @Test
  public void shouldAddValidPlace() {
    Row row = new Row();
    Place place = new Place(22);
    row.addPlace(place);
    assertTrue(row.exists(place));
    assertEquals(row, place.getRow());
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForAddindNullPlace() {
    new Row().addPlace(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseErrorForAddingPlaceWhichAlreadyExist() {
    Row row = new Row();
    row.addPlace(new Place(22));
    row.addPlace(new Place(22));
  }

  @Test
  public void shouldSpecifyNumber() {
    Row row = new Row();
    row.setNumber(4);
    assertEquals(4, row.getNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNegativeNumber() {
    Row row = new Row();
    row.setNumber( -7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForZeroNumber() {
    Row row = new Row();
    row.setNumber(0);
  }

  @Test
  public void shouldNotAddDuplicatePlace() {
    fail("Not yet implemented");
  }
}
