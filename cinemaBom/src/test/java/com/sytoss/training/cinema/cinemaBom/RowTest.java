package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class RowTest {

  static Row row;

  @BeforeClass
  public static void init() {
    row = new Row();
  }

  @Test
  public void testSetNumberCorrect() {
    row.setNumber(4);
    assertEquals(4, row.getNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNumberNegative() {
    row.setNumber( -7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNumberZero() {
    row.setNumber(0);
  }

  @Test
  public void testSetStatusCorrect() {
    PlacesStatuses status = PlacesStatuses.enable;
    row.setStatus(status);
    assertEquals(status, row.getStatus());
  }

  @Test(expected = NullPointerException.class)
  public void testSetStatusNull() {
    row.setStatus(null);
  }
}
