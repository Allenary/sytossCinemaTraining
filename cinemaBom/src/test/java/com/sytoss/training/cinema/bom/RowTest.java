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

  @Test(expected = ReassignObjectException.class)
  public void shouldRaiseErrorForReassigningRowToAnotherRoom() {
    Room oldRoom = new Room();
    Room newRoom = new Room();
    Row row = new Row();
    row.setRoom(oldRoom);
    row.setRoom(newRoom);
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

  @Test(expected = DuplicateInsertionException.class)
  public void shouldNotAddDuplicatePlace() {
    Row row = new Row();
    Place place = new Place(2);
    row.addPlace(place);
    row.addPlace(place);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldNotAddDuplicateNumberPlace() {
    Row row = new Row();
    row.addPlace(new Place(2));
    row.addPlace(new Place(2));
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseErrorForAddingPlaceWithNumberWhichAlreadyExist() {
    Row row = new Row();
    row.addPlace(new Place(22));
    row.addPlace(new Place(22));
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldRaiseErrorWhenAddPlaceAssignedToAnotherRow() {
    Row oldRow = new Row();
    Row newRow = new Row();
    Place place = new Place(2);
    oldRow.addPlace(place);
    newRow.addPlace(place);

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

}
