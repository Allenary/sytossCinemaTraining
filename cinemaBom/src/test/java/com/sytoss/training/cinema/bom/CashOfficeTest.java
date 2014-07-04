package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CashOfficeTest {

  // Number field test cover
  @Test
  public void shouldSpecifyNumber() {
    CashOffice cashoffice = new CashOffice();
    cashoffice.setNumber(12);
    assertEquals(12, cashoffice.getNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForZeroNumber() {
    CashOffice cashoffice = new CashOffice();
    cashoffice.setNumber(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNegativeNumber() {
    CashOffice cashoffice = new CashOffice();
    cashoffice.setNumber( -7);
  }

  // CashOffice[1] - [1]Cinema reference test cover

  @Test
  public void shouldSpecifyCinemaInstance() {
    CashOffice cashoffice = new CashOffice();
    Cinema cinema = new Cinema();
    cinema.setName("vasilisa");
    cinema.setAddress("kharkov");
    cashoffice.setCinema(cinema);
    assertEquals(cinema, cashoffice.getCinema());
    assertTrue(cinema.exists(cashoffice));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullCinemaInstance() {
    CashOffice cashoffice = new CashOffice();
    cashoffice.setCinema(null);
  }
}
