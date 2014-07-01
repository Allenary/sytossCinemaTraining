package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class CashofficeTest {

  static Cashoffice cashoffice;

  @BeforeClass
  public static void initializeCashoffice() {
    cashoffice = new Cashoffice();
  }

  @Test
  public void testSetNumberCorrect() {
    int cashofficeNumber = 12;
    cashoffice.setNumber(cashofficeNumber);
    assertEquals(cashofficeNumber, cashoffice.getNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNumberNegative() {
    int cashofficeNumber = -8;
    cashoffice.setNumber(cashofficeNumber);
  }

  @Test
  public void testSetCinemaCorrect() {
    Cinema cinema = new Cinema();
    cashoffice.setCinema(cinema);
    assertEquals(cinema, cashoffice.getCinema());
  }

  @Test(expected = NullPointerException.class)
  public void testSetCinemaNull() {
    cashoffice.setCinema(null);

  }
}
