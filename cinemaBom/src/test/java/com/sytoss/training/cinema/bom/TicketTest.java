package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bom.exception.NullObjectInsertionException;

public class TicketTest {

  @Test
  public void shouldAddValidSeance() {
    Ticket ticket = new Ticket();
    Seance seance = new Seance();
    ticket.setSeance(seance);
    assertEquals(seance, ticket.getSeance());
    assertTrue(seance.contains(ticket));
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

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForAddingNullSeance() {
    Ticket ticket = new Ticket();
    ticket.setSeance(null);
  }

}
