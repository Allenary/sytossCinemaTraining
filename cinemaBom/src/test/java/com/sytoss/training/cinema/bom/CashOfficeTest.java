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
    assertTrue(cinema.existCashOffice(cashoffice));
  }

  @Test
  public void shouldReassignCashOfficeToAnotherCinema() {
    CashOffice cashoffice = new CashOffice();
    Cinema firstCinema = new Cinema("First");
    Cinema secondCinema = new Cinema("Second");
    cashoffice.setCinema(firstCinema);
    cashoffice.setCinema(secondCinema);
    assertEquals(secondCinema, cashoffice.showCinema());
    assertTrue(secondCinema.existCashOffice(cashoffice));
    assertFalse(firstCinema.existCashOffice(cashoffice));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullCinemaInstance() {
    new CashOffice().setCinema(null);
  }

  @Test
  public void shouldAddValidTicket() {
    CashOffice cashOffice = new CashOffice();
    Ticket ticket = new Ticket(new Place(1));
    cashOffice.addTicket(ticket);
    assertTrue(cashOffice.exists(ticket));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForAddNullTicket() {
    new CashOffice().addTicket(null);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseExceptionForAddDuplicateTicket() {
    CashOffice cashOffice = new CashOffice();
    Ticket ticket = new Ticket(new Place(1));
    cashOffice.addTicket(ticket);
    cashOffice.addTicket(ticket);
  }
}
