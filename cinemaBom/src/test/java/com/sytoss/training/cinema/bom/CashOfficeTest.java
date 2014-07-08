package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bom.exception.DuplicateInsertionException;
import bom.exception.NullObjectInsertionException;

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
  public void shouldSetCinema() {
    CashOffice cashoffice = new CashOffice();
    Cinema cinema = new Cinema();
    cinema.setName("Kronverk");
    cinema.setAddress("kharkov");
    cashoffice.setCinema(cinema);
    assertEquals(cinema, cashoffice.showCinema());
    assertTrue(cinema.exists(cashoffice));
  }

  @Test
  public void shouldReassignCashOfficeToAnotherCinema() {
    CashOffice cashoffice = new CashOffice();
    Cinema firstCinema = new Cinema("First");
    Cinema secondCinema = new Cinema("Second");
    cashoffice.setCinema(firstCinema);
    cashoffice.setCinema(secondCinema);
    assertEquals(secondCinema, cashoffice.showCinema());
    assertTrue(secondCinema.exists(cashoffice));
    assertFalse(firstCinema.exists(cashoffice));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullCinemaInstance() {
    CashOffice cashoffice = new CashOffice();
    cashoffice.setCinema(null);
  }

  @Test
  public void shouldAddValidTicket() {
    CashOffice cashOffice = new CashOffice();
    Ticket ticket = new Ticket();
    cashOffice.addTicket(ticket);
    assertTrue(cashOffice.exists(ticket));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForAddNullTicket() {
    new CashOffice().addTicket(null);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseExceptionForAddDuplicateTicket() {
    CashOffice cashOffice = new CashOffice();
    Ticket ticket = new Ticket();
    cashOffice.addTicket(ticket);
    cashOffice.addTicket(ticket);
  }
}
