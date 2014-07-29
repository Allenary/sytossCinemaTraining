package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import com.sytoss.training.cinema.exception.*;

public class CashOfficeTest {

  @Test
  public void shouldSpecifyNumber() {
    CashOffice cashoffice = new CashOffice();
    cashoffice.setNumber(12);
    assertEquals(12, cashoffice.getNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForZeroNumber() {
    CashOffice cashoffice = new CashOffice();
    cashoffice.setNumber(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNegativeNumber() {
    CashOffice cashoffice = new CashOffice();
    cashoffice.setNumber( -7);
  }

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
  public void shouldRaiseExceptionForNullCinemaInstance() {
    new CashOffice().setCinema(null);
  }

  @Test
  public void shouldAddValidTicket() {
    CashOffice cashOffice = new CashOffice();
    Ticket ticket = new Ticket(new Place(1));
    cashOffice.addTicket(ticket);
    Iterator<Ticket> tickets = cashOffice.showTikets();
    assertTrue(tickets.hasNext());
    tickets.next();
    assertFalse(tickets.hasNext());
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

  @Test
  public void shouldShowNoTicketsIfTicketsNotAdded() {
    assertFalse(new CashOffice().showTikets().hasNext());
  }

  @Test
  public void shouldAddTwoTickets() {
    CashOffice cashOffice = new CashOffice();
    cashOffice.addTicket(new Ticket(new Place(1)));
    cashOffice.addTicket(new Ticket(new Place(2)));
    Iterator<Ticket> tickets = cashOffice.showTikets();
    assertTrue(tickets.hasNext());
    tickets.next();
    assertTrue(tickets.hasNext());
    tickets.next();
    assertFalse(tickets.hasNext());
  }

  @Test
  public void shouldReturnFalseIfTicketsNoExist() {
    assertFalse(new CashOffice().exists(new Ticket(new Place(1))));
  }

  @Test
  public void shouldReturnTrueIfTicketExist() {
    CashOffice cashOffice = new CashOffice();
    Ticket ticket = new Ticket(new Place(1));
    cashOffice.addTicket(ticket);
    assertTrue(cashOffice.exists(ticket));
  }

  @Test
  public void shouldSaleEnableTicket() {
    Ticket ticket = new Ticket(new Place(2));
    Seance seance = new Seance();
    ticket.setSeance(seance);
    CashOffice cashOffice = new CashOffice();
    cashOffice.saleTicket(ticket);
    assertEquals(TicketStatus.SOLD, ticket.getStatus());
    assertFalse(seance.getAvaliableTickets().hasNext());
    Iterator<Ticket> tickets = cashOffice.showTikets();
    assertTrue(tickets.hasNext());
    tickets.next();
    assertFalse(tickets.hasNext());
  }

  @Test
  public void shouldSaleReservedTicket() {
    Ticket ticket = new Ticket(new Place(2));
    Seance seance = new Seance();
    ticket.setSeance(seance);
    CashOffice cashOffice = new CashOffice();
    cashOffice.reserveTicket(ticket);
    cashOffice.saleTicket(ticket);
    assertEquals(TicketStatus.SOLD, ticket.getStatus());
    assertFalse(seance.getAvaliableTickets().hasNext());
    Iterator<Ticket> tickets = cashOffice.showTikets();
    assertTrue(tickets.hasNext());
    tickets.next();
    assertFalse(tickets.hasNext());
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionWhenSaleSameTicketTwice() {
    Ticket ticket = new Ticket(new Place(2));
    CashOffice cashOffice = new CashOffice();
    cashOffice.saleTicket(ticket);
    cashOffice.saleTicket(ticket);
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionWhenSaleTicketSoldByAnotherCashOffice() {
    Ticket ticket = new Ticket(new Place(2));
    CashOffice cashOffice = new CashOffice();
    CashOffice anotherCashOffice = new CashOffice();
    anotherCashOffice.saleTicket(ticket);
    cashOffice.saleTicket(ticket);
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionWhenSaleTicketWithStateNotForSale() {
    Ticket ticket = new Ticket(new Place(2));
    Seance seance = new Seance();
    ticket.setSeance(seance);
    ticket.changeStatus(TicketStatus.NOT_FOR_SALE);
    CashOffice cashOffice = new CashOffice();
    cashOffice.saleTicket(ticket);

  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionWhenSaleNullTicket() {
    new CashOffice().saleTicket(null);
  }

  @Test
  public void shouldReserveEnableTicket() {
    Ticket ticket = new Ticket(new Place(2));
    Seance seance = new Seance();
    ticket.setSeance(seance);
    CashOffice cashOffice = new CashOffice();
    cashOffice.reserveTicket(ticket);
    assertEquals(TicketStatus.RESERVED, ticket.getStatus());
    assertFalse(seance.getAvaliableTickets().hasNext());
    Iterator<Ticket> tickets = cashOffice.showTikets();
    assertTrue(tickets.hasNext());
    tickets.next();
    assertFalse(tickets.hasNext());
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionWhenReserveSameTicketTwice() {
    Ticket ticket = new Ticket(new Place(2));
    CashOffice cashOffice = new CashOffice();
    cashOffice.reserveTicket(ticket);
    cashOffice.reserveTicket(ticket);
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionWhenReserveNullTicket() {
    new CashOffice().reserveTicket(null);
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionWhenReserveSoldTicket() {
    Ticket ticket = new Ticket(new Place(2));
    CashOffice cashOffice = new CashOffice();
    cashOffice.saleTicket(ticket);
    cashOffice.reserveTicket(ticket);
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionWhenReserveTicketWithNotForSale() {
    Ticket ticket = new Ticket(new Place(2));
    CashOffice cashOffice = new CashOffice();
    cashOffice.saleTicket(ticket);
    cashOffice.reserveTicket(ticket);
  }

  @Test
  public void shouldReturnSoldTicketForOpenedSeance() {
    Ticket ticket = new Ticket(new Place(2));
    Seance seance = new Seance();
    ticket.setSeance(seance);
    CashOffice cashOffice = new CashOffice();
    cashOffice.saleTicket(ticket);
    cashOffice.returnTicket(ticket);
    assertEquals(TicketStatus.ENABLE, ticket.getStatus());
    Iterator<Ticket> avaliableTickets = seance.getAvaliableTickets();
    assertTrue(avaliableTickets.hasNext());
    avaliableTickets.next();
    assertFalse(avaliableTickets.hasNext());

    Iterator<Ticket> tickets = cashOffice.showTikets();
    assertTrue(tickets.hasNext());
    tickets.next();
    assertFalse(tickets.hasNext());
  }

  @Test
  public void shouldReturnSoldTicketForCanceledSeance() {
    Ticket ticket = new Ticket(new Place(2));
    Seance seance = new Seance();
    ticket.setSeance(seance);
    CashOffice cashOffice = new CashOffice();
    cashOffice.saleTicket(ticket);
    seance.cancel();
    cashOffice.returnTicket(ticket);
    assertEquals(TicketStatus.NOT_FOR_SALE, ticket.getStatus());
    Iterator<Ticket> avaliableTickets = seance.getAvaliableTickets();
    assertFalse(avaliableTickets.hasNext());

    Iterator<Ticket> tickets = cashOffice.showTikets();
    assertTrue(tickets.hasNext());
    tickets.next();
    assertFalse(tickets.hasNext());
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionForReturnSoldTicketForClosedSeance() {
    Ticket ticket = new Ticket(new Place(2));
    Seance seance = new Seance();
    ticket.setSeance(seance);
    CashOffice cashOffice = new CashOffice();
    cashOffice.saleTicket(ticket);
    seance.setStatus(SeanceStatus.CLOSED);
    cashOffice.returnTicket(ticket);

  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionWhenReturnNullTicket() {
    new CashOffice().returnTicket(null);
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionForReturnReservedTicket() {
    Ticket ticket = new Ticket(new Place(2));
    CashOffice cashOffice = new CashOffice();
    cashOffice.reserveTicket(ticket);
    cashOffice.returnTicket(ticket);
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionForReturnNotForSaleTicket() {
    Ticket ticket = new Ticket(new Place(2));
    CashOffice cashOffice = new CashOffice();
    ticket.changeStatus(TicketStatus.NOT_FOR_SALE);
    cashOffice.returnTicket(ticket);
  }

  @Test(expected = CashOfficeManipulationException.class)
  public void shouldRaiseExceptionForReturnEnableTicket() {
    Ticket ticket = new Ticket(new Place(2));
    CashOffice cashOffice = new CashOffice();
    cashOffice.returnTicket(ticket);
  }
}
