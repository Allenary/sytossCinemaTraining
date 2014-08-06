package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.junit.Test;

import com.sytoss.training.cinema.exception.DuplicateInsertionException;
import com.sytoss.training.cinema.exception.NullObjectInsertionException;
import com.sytoss.training.cinema.exception.ReassignObjectException;
import com.sytoss.training.cinema.exception.SeanceChangeStateException;

public class SeanceTest {

  @Test
  public void shouldAddValidMovie() {
    Seance seance = new Seance();
    Movie movie = new Movie("mr Nobody");
    seance.setMovie(movie);
    assertEquals(movie, seance.getMovie());
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForAddNullMovie() {
    new Seance().setMovie(null);
  }

  @Test
  public void shouldAddValidTicket() {
    Seance seance = new Seance();
    Ticket ticket = new Ticket(new Place(1));
    seance.addTicket(ticket);
    assertEquals(seance, ticket.getSeance());
    Iterator<Ticket> tickets = seance.getTickets();
    assertTrue(tickets.hasNext());
    tickets.next();
    assertFalse(tickets.hasNext());
  }

  @Test
  public void shouldVerifyExistanceOfTicket() {
    Seance seance = new Seance();
    Ticket ticket = new Ticket(new Place(1));
    seance.addTicket(ticket);
    assertEquals(seance, ticket.getSeance());
    assertTrue(seance.existsTicket(ticket));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForAddingNullTicket() {
    new Seance().addTicket(null);
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldRaiseExceptionForAddTicketAssignedToAnotherSeance() {
    Seance oldSeance = new Seance(new Room("green"), new GregorianCalendar(2014, 10, 5, 12, 30));
    Ticket ticket = new Ticket(new Place(1));
    ticket.setSeance(oldSeance);
    Seance newSeance = new Seance(new Room("red"), new GregorianCalendar(2014, 10, 5, 12, 30));
    newSeance.addTicket(ticket);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldNotAddDuplicateTicket() {
    Seance seance = new Seance();
    Ticket ticket = new Ticket(new Place(1));
    seance.addTicket(ticket);
    seance.addTicket(ticket);

  }

  @Test
  public void shouldSpecifyStartDateTime() {
    Seance seance = new Seance();
    Calendar calendar = new GregorianCalendar(2014, 6, 22);
    seance.setStartDateTime(calendar);
    assertEquals(calendar, seance.getStartDateTime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNullStartDateTime() {
    Seance seance = new Seance();
    seance.setStartDateTime(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNullStatus() {
    Seance seance = new Seance();
    seance.setStatus(null);
  }

  @Test
  public void shouldSpecifyStatus() {
    Seance seance = new Seance();
    seance.setStatus(SeanceStatus.CANCELED);
    assertEquals(SeanceStatus.CANCELED, seance.getStatus());
  }

  @Test
  public void shouldAddValidRoom() {
    Seance seance = new Seance();
    Room room = new Room("b");
    room.setName("White");
    seance.setRoom(room);
    assertEquals(room, seance.getRoom());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNullRoom() {
    Seance seance = new Seance();
    seance.setRoom(null);
  }

  @Test
  public void shouldReturnTrueIfSeanceOpened() {
    assertTrue(new Seance().isOpen());
  }

  @Test
  public void shouldReturnFalseIfSeanceNotOpened() {
    Seance seance = new Seance();
    seance.cancel();
    assertFalse(seance.isOpen());
  }

  @Test
  public void shouldCancelSeance() {
    Seance seance = new Seance();
    Ticket soldTicket = new Ticket(new Place(1));
    seance.addTicket(soldTicket);
    new CashOffice().saleTicket(soldTicket);
    Ticket forSaleTicket = new Ticket(new Place(1));
    seance.addTicket(forSaleTicket);
    seance.cancel();
    assertEquals(SeanceStatus.CANCELED, seance.getStatus());
    assertEquals(TicketStatus.NOT_FOR_SALE, forSaleTicket.getStatus());
    assertEquals(TicketStatus.SOLD, soldTicket.getStatus());
    assertFalse(seance.getAvaliableTickets().hasNext());
  }

  @Test(expected = SeanceChangeStateException.class)
  public void shouldRaiseExceptionForCancelClosedSeance() {
    Seance seance = new Seance();
    seance.setStatus(SeanceStatus.CLOSED);
    seance.cancel();
  }

  @Test
  public void shouldDisableEnabledTicket() {
    Ticket ticket = new Ticket(new Place(1));
    Seance seance = new Seance();
    seance.addTicket(ticket);
    seance.disableTicket(ticket);
    assertEquals(TicketStatus.NOT_FOR_SALE, ticket.getStatus());
    assertFalse(seance.getAvaliableTickets().hasNext());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionWhenDisableTicketFromAnotherSeance() {
    Ticket ticket = new Ticket(new Place(1));
    Seance seance = new Seance();
    seance.disableTicket(ticket);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionWhenDisableSoldTicket() {
    Ticket ticket = new Ticket(new Place(1));
    Seance seance = new Seance();
    seance.addTicket(ticket);
    new CashOffice().saleTicket(ticket);
    seance.disableTicket(ticket);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionWhenDisableNullTicket() {
    new Seance().disableTicket(null);
  }

  @Test
  public void shouldDisableRow() {
    Room room = new Room("blue");
    Row row = new Row(1, room);
    Ticket ticket1 = new Ticket(new Place(1, row));
    Ticket ticket2 = new Ticket(new Place(2, row));
    Row anotherRow = new Row(2, room);
    Ticket ticketFromAnotherRow = new Ticket(new Place(1, anotherRow));
    Seance seance = new Seance();
    seance.setRoom(room);
    seance.addTicket(ticket1);
    seance.addTicket(ticket2);
    seance.addTicket(ticketFromAnotherRow);
    seance.disableRow(row);
    assertEquals(TicketStatus.NOT_FOR_SALE, ticket1.getStatus());
    assertEquals(TicketStatus.NOT_FOR_SALE, ticket2.getStatus());
    assertEquals(TicketStatus.ENABLE, ticketFromAnotherRow.getStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForDisableRowFromAnotherRoom() {
    Seance seance = new Seance();
    Room roomOfSeance = new Room("red");
    Room anotherRoom = new Room("blue");
    seance.setRoom(roomOfSeance);
    seance.disableRow(new Row(1, anotherRoom));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForDisableNullRow() {
    new Seance().disableRow(null);
  }
}
