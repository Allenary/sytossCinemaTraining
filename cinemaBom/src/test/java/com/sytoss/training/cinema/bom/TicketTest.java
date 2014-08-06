package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sytoss.training.cinema.exception.NullObjectInsertionException;
import com.sytoss.training.cinema.exception.ReassignObjectException;

public class TicketTest {

  @Test
  public void shouldAddValidSeance() {
    Ticket ticket = new Ticket(new Place(1));
    Seance seance = new Seance();
    ticket.setSeance(seance);
    assertEquals(seance, ticket.getSeance());
    assertTrue(seance.existsTicket(ticket));
  }

  @Test(expected = ReassignObjectException.class)
  public void shouldNotReassignTicketToAnotherSeance() {
    Ticket ticket = new Ticket(new Place(1));
    Seance oldSeance = new Seance();
    Seance newSeance = new Seance();
    ticket.setSeance(oldSeance);
    ticket.setSeance(newSeance);
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForAddingNullSeance() {
    Ticket ticket = new Ticket(new Place(1));
    ticket.setSeance(null);
  }

  @Test
  public void shouldSpecifyStatus() {
    Ticket ticket = new Ticket(new Place(1));
    ticket.changeStatus(TicketStatus.ENABLE);
    assertEquals(TicketStatus.ENABLE, ticket.getStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNullStatus() {
    Ticket ticket = new Ticket(new Place(1));
    ticket.changeStatus(null);
  }

  @Test
  public void shouldSpecifyPrice() {
    Ticket ticket = new Ticket(new Place(1));
    ticket.setPrice(22.43);
    assertEquals(22.43, ticket.getPrice(), 0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNegativePrice() {
    Ticket ticket = new Ticket(new Place(1));
    ticket.setPrice( -5.53);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForZeroPrice() {
    Ticket ticket = new Ticket(new Place(1));
    ticket.setPrice(0);
  }

  // Ticket[1] - [1]Place reference test cover
  @Test
  public void shouldSetPlace() {
    Ticket ticket = new Ticket(new Place(1));
    Place place = new Place(1);
    place.setNumber(72);
    ticket.setPlace(place);
    assertEquals(place, ticket.getPlace());
    assertEquals(72, ticket.getPlace().getNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNullPlaceInstance() {
    Ticket ticket = new Ticket(new Place(1));
    ticket.setPlace(null);
  }

  @Test
  public void shouldSetCashOffice() {
    CashOffice cashOffice = new CashOffice(2);
    Ticket ticket = new Ticket(new Place(1));
    ticket.setCashOffice(cashOffice);
    assertEquals(cashOffice, ticket.getCashOffice());
    assertTrue(cashOffice.exists(ticket));
  }
}
