package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bom.exception.*;

public class TicketTest {

  @Test
  public void shouldAddValidSeance() {
    Ticket ticket = new Ticket(new Place(1));
    Seance seance = new Seance();
    ticket.setSeance(seance);
    assertEquals(seance, ticket.getSeance());
    assertTrue(seance.contains(ticket));
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldNotReassignTicketToAnotherSeance() {
    Ticket ticket = new Ticket(new Place(1));
    Seance oldSeance = new Seance();
    Seance newSeance = new Seance();
    ticket.setSeance(oldSeance);
    ticket.setSeance(newSeance);
    assertEquals(oldSeance, ticket.getSeance());
    assertTrue(oldSeance.contains(ticket));
    assertFalse(newSeance.contains(ticket));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForAddingNullSeance() {
    Ticket ticket = new Ticket();
    ticket.setSeance(null);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseErrorForSetSeanceWhichAlreadyHasTicketOnSamePlace() {
    Seance seance = new Seance();
    Place place = new Place(8);
    Ticket oldTicket = new Ticket(place);
    oldTicket.setSeance(seance);
    Ticket newTicket = new Ticket(place);
    newTicket.setSeance(seance);
  }

  @Test
  public void shouldSpecifyStatus() {
    Ticket ticket = new Ticket();
    ticket.changeStatus(TicketStatus.ENABLE);
    assertEquals(TicketStatus.ENABLE, ticket.getStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullStatus() {
    Ticket ticket = new Ticket();
    ticket.changeStatus(null);
  }

  @Test
  public void shouldSpecifyPrice() {
    Ticket ticket = new Ticket();
    ticket.setPrice(22.43);
    assertEquals(22.43, ticket.getPrice(), 0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNegativePrice() {
    Ticket ticket = new Ticket();
    ticket.setPrice( -5.53);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForZeroPrice() {
    Ticket ticket = new Ticket();
    ticket.setPrice(0);
  }

  // Ticket[1] - [1]Place reference test cover
  @Test
  public void shouldSpecifyPlaceInstance() {
    Ticket ticket = new Ticket();
    Place place = new Place();
    place.setNumber(72);
    ticket.setPlace(place);
    assertEquals(place, ticket.getPlace());
    assertEquals(72, ticket.getPlace().getNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullPlaceInstance() {
    Ticket ticket = new Ticket();
    ticket.setPlace(null);
  }

}
