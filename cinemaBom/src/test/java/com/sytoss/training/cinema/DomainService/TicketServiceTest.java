package com.sytoss.training.cinema.DomainService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Ticket;

public class TicketServiceTest {

  @Test
  public void shouldReturnTrueIfAllTicketsSoldBySameCashOffice() {

    Ticket ticket1 = new Ticket();
    ticket1.setCashOffice(new CashOffice(1));
    Ticket ticket2 = new Ticket();
    ticket2.setCashOffice(new CashOffice(1));
    Ticket ticket3 = new Ticket();
    ticket3.setCashOffice(new CashOffice(1));

    List<Ticket> tickets = new ArrayList<Ticket>();
    tickets.add(ticket1);
    tickets.add(ticket2);
    tickets.add(ticket3);

    assertTrue(new TicketService().equalsCashOfficeID(tickets));
  }

  @Test
  public void shouldReturnFalseIfNotAllTicketsSoldBySameCashOffice() {

    Ticket ticket1 = new Ticket();
    ticket1.setCashOffice(new CashOffice(1));
    Ticket ticket2 = new Ticket();
    ticket2.setCashOffice(new CashOffice(1));
    Ticket ticket3 = new Ticket();
    ticket3.setCashOffice(new CashOffice(2));

    List<Ticket> tickets = new ArrayList<Ticket>();
    tickets.add(ticket1);
    tickets.add(ticket2);
    tickets.add(ticket3);

    assertFalse(new TicketService().equalsCashOfficeID(tickets));
  }

  @Test
  public void shouldReturnFalseIfListEmpty() {
    List<Ticket> tickets = new ArrayList<Ticket>();

    assertFalse(new TicketService().equalsCashOfficeID(tickets));
  }

  @Test
  public void shouldReturnFalseIfNoCashOfficeNumber() {
    List<Ticket> tickets = new ArrayList<Ticket>();
    Ticket ticket1 = new Ticket();
    ticket1.setCashOffice(new CashOffice(1));
    Ticket ticket2 = new Ticket();

    tickets.add(ticket1);
    tickets.add(ticket2);

    assertFalse(new TicketService().equalsCashOfficeID(tickets));
  }

}
