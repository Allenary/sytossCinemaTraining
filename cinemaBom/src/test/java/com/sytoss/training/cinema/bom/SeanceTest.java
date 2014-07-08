package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Ignore;
import org.junit.Test;

import bom.exception.DuplicateInsertionException;
import bom.exception.NullObjectInsertionException;
import bom.exception.ReassignObjectException;

public class SeanceTest {

  @Test
  public void shouldAddValidMovie() {
    Seance seance = new Seance();
    Movie movie = new Movie("mr Nobody");
    seance.setMovie(movie);
    assertEquals(movie, seance.getMovie());
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForAddNullMovie() {
    new Seance().setMovie(null);
  }

  @Test
  public void shouldAddValidTicket() {
    Seance seance = new Seance();
    Ticket ticket = new Ticket(new Place(1));
    seance.addTicket(ticket);
    assertEquals(seance, ticket.getSeance());
    assertTrue(seance.contains(ticket));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForAddingNullTicket() {
    new Seance().addTicket(null);
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldRaiseErrorForAddTicketAssignedToAnotherSeance() {
    Seance oldSeance = new Seance();
    Ticket ticket = new Ticket(new Place(1));
    ticket.setSeance(oldSeance);
    Seance newSeance = new Seance();
    newSeance.addTicket(ticket);
    assertEquals(oldSeance, ticket.getSeance());
    assertTrue(oldSeance.contains(ticket));
    assertFalse(newSeance.contains(ticket));
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldNotAddDuplicateTicket() {
    Seance seance = new Seance();
    Ticket ticket = new Ticket(new Place(1));
    seance.addTicket(ticket);
    seance.addTicket(ticket);

  }

  @Test(expected = NullObjectInsertionException.class)
  public void ShouldNotAddTicketWithoutPlace() {
    new Seance().addTicket(new Ticket());
  }

  @Ignore
  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseErrorForAddTicketWithPlaceWhichAlreadyHasTicket() {
    Seance seance = new Seance();
    Ticket oldTicket = new Ticket();
    Place place = new Place();
    oldTicket.setPlace(place);
    seance.addTicket(oldTicket);
    Ticket newTicket = new Ticket();
    newTicket.setPlace(place);
    seance.addTicket(newTicket);
  }

  @Test
  public void shouldSpecifyStartDateTime() {
    Seance seance = new Seance();
    Calendar calendar = new GregorianCalendar(2014, 6, 22);
    seance.setStartDateTime(calendar);
    assertEquals(calendar, seance.getStartDateTime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForPastStartDateTime() {
    Seance seance = new Seance();
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    seance.setStartDateTime(calendar);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullStartDateTime() {
    Seance seance = new Seance();
    seance.setStartDateTime(null);
  }

  // Status field test cover
  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullStatus() {
    Seance seance = new Seance();
    seance.setStatus(null);
  }

  @Test
  public void shouldSpecifyStatus() {
    Seance seance = new Seance();
    seance.setStatus(SeanceStatus.CANCEL);
    assertEquals(SeanceStatus.CANCEL, seance.getStatus());
  }

  // Seance[1] - [1]Room reference test cover
  @Test
  public void shouldAddValidRoom() {
    Seance seance = new Seance();
    Room room = new Room();
    room.setName("White");
    seance.setRoom(room);
    assertEquals(room, seance.getRoom());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullRoom() {
    Seance seance = new Seance();
    seance.setRoom(null);
  }

}
