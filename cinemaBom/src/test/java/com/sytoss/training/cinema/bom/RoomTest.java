package com.sytoss.training.cinema.bom;

import static org.junit.Assert.*;

import org.junit.Test;

import bom.exception.NullObjectInsertionException;

import com.sytoss.training.cinema.bom.Room;

public class RoomTest {

  @Test
  public void shouldAddRowToRoom() {
    Room room = new Room();
    Row row = new Row(1);
    room.addRow(row);
    assertEquals(room, row.getRoom());
    assertTrue(room.exists(row));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForAddingNullRow() {
    new Room().addRow(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForEmptyName() {
    Room room = new Room();
    room.setName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullName() {
    Room room = new Room();
    room.setName(null);
  }

  @Test
  public void shouldSpecifyName() {
    Room room = new Room();
    room.setName("White");
    assertEquals("White", room.getName());
  }

  @Test
  public void shouldSetValidCinema() {
    Room room = new Room();
    Cinema cinema = new Cinema();
    room.setCinema(cinema);
    assertEquals(cinema, room.getCinema());
    assertTrue(cinema.exists(room));

  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForAddingNullCinema() {
    new Room().setCinema(null);
  }

  @Test
  public void shouldNotAddDuplicateRow() {
    fail("Not yet implemented");
  }
}
