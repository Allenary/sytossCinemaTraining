package com.sytoss.training.cinema.bom;

import static org.junit.Assert.*;

import org.junit.Test;

import bom.exception.DuplicateInsertionException;
import bom.exception.NullObjectInsertionException;
import bom.exception.ReassignObjectException;

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

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseErrorForAddDuplicateRow() {
    Room room = new Room();
    Row row = new Row(1);
    room.addRow(row);
    room.addRow(row);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseErrorForAddDuplicateNumberRow() {
    Room room = new Room();
    room.addRow(new Row(1));
    room.addRow(new Row(1));
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldRaiseErrorForAddRowAssignedToAnotherRoom() {
    Room oldRoom = new Room();
    Room newRoom = new Room();
    Row row = new Row();
    oldRoom.addRow(row);
    newRoom.addRow(row);
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

}
