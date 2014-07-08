package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bom.exception.DuplicateInsertionException;
import bom.exception.NullObjectInsertionException;
import bom.exception.ReassignObjectException;

public class RowTest {

  @Test
  public void shouldSetValidRoom() {
    Row row = new Row(1);
    Room room = new Room("a");
    row.setRoom(room);
    assertEquals(room, row.getRoom());
    assertTrue(room.exists(row));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForSetNullRoom() {
    new Row(1).setRoom(null);
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldRaiseExceptionForReassigningRowToAnotherRoom() {
    Room oldRoom = new Room("blue");
    Room newRoom = new Room("white");
    Row row = new Row(1);
    row.setRoom(oldRoom);
    row.setRoom(newRoom);
  }

  @Test
  public void shouldAddValidPlace() {
    Row row = new Row(1);
    Place place = new Place(22);
    row.addPlace(place);
    assertTrue(row.exists(place));
    assertEquals(row, place.getRow());
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForAddindNullPlace() {
    new Row(1).addPlace(null);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldNotAddDuplicatePlace() {
    Row row = new Row(1);
    Place place = new Place(2);
    row.addPlace(place);
    row.addPlace(place);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldNotAddDuplicateNumberPlace() {
    Row row = new Row(1);
    row.addPlace(new Place(2));
    row.addPlace(new Place(2));
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseExceptionForAddingPlaceWithNumberWhichAlreadyExist() {
    Row row = new Row(1);
    row.addPlace(new Place(22));
    row.addPlace(new Place(22));
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldRaiseExceptionWhenAddPlaceAssignedToAnotherRow() {
    Row oldRow = new Row(1);
    Row newRow = new Row(1);
    Place place = new Place(2);
    oldRow.addPlace(place);
    newRow.addPlace(place);

  }

  @Test
  public void shouldSpecifyNumber() {
    Row row = new Row(1);
    row.setNumber(4);
    assertEquals(4, row.getNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNegativeNumber() {
    Row row = new Row(1);
    row.setNumber( -7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForZeroNumber() {
    Row row = new Row(1);
    row.setNumber(0);
  }

}
