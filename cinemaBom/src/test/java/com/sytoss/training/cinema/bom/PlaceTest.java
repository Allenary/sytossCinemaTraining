package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sytoss.training.cinema.exception.NullObjectInsertionException;
import com.sytoss.training.cinema.exception.ReassignObjectException;

public class PlaceTest {

  @Test
  public void shouldAddValidRow() {
    Place place = new Place(7);
    Row row = new Row(1);
    place.setRow(row);
    assertTrue(row.exists(place));
    assertEquals(row, place.getRow());

  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForAddingNullRow() {
    new Place(1).setRow(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNegativeNumber() {
    Place place = new Place(1);
    place.setNumber( -8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForZeroNumber() {
    Place place = new Place(1);
    place.setNumber(0);
  }

  @Test
  public void shouldSpecifyNumber() {
    Place place = new Place(1);
    place.setNumber(12);
    assertEquals(12, place.getNumber());
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldRaiseExceptionWhenReassignPlaceToAnotherRow() {
    Row oldRow = new Row(1);
    Row newRow = new Row(2);
    Place place = new Place(2);
    place.setRow(oldRow);
    place.setRow(newRow);
  }
}
