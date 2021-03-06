package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

import org.junit.Test;

import com.sytoss.training.cinema.exception.NullObjectInsertionException;
import com.sytoss.training.cinema.exception.ReassignObjectException;

public class RoomTest {

  @Test
  public void shouldAddRowToRoom() {
    Room room = new Room("blue");
    Row row = new Row(1);
    room.addRow(row);
    assertEquals(room, row.getRoom());
    Iterator<Row> rows = room.getAllRows();
    assertTrue(rows.hasNext());
    rows.next();
    assertFalse(rows.hasNext());
  }

  public void shouldVerifyExistanceOfRow() {
    Room room = new Room("blue");
    Row row = new Row(1);
    assertFalse(room.exists(row));
    room.addRow(row);
    assertEquals(room, row.getRoom());
    assertTrue(room.exists(row));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForAddingNullRow() {
    new Room("blue").addRow(null);
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldNotAddDuplicateRow() {
    Room room = new Room("blue");
    Row row = new Row(1);
    room.addRow(row);
    room.addRow(row);
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldNotAddDuplicateNumberRow() {
    Room room = new Room("blue");
    room.addRow(new Row(1));
    room.addRow(new Row(1));
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldRaiseExceptionForAddRowAssignedToAnotherRoom() {
    Room oldRoom = new Room("blue");
    Room newRoom = new Room("white");
    Row row = new Row(1);
    oldRoom.addRow(row);
    newRoom.addRow(row);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForEmptyName() {
    new Room("blue").setName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNullName() {
    new Room("blue").setName(null);
  }

  @Test
  public void shouldSpecifyName() {
    Room room = new Room("blue");
    room.setName("White");
    assertEquals("White", room.getName());
  }
}
