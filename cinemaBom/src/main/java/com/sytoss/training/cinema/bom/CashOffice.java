package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sytoss.training.cinema.exception.CashOfficeManipulationException;
import com.sytoss.training.cinema.exception.DuplicateInsertionException;
import com.sytoss.training.cinema.exception.NullObjectInsertionException;

public class CashOffice {

  private int number;

  private Cinema cinema;

  private List<Ticket> tickets;

  public CashOffice(int number) {
    this();
    this.number = number;
  }

  public CashOffice() {
    tickets = new ArrayList<Ticket>();
  }

  public Cinema showCinema() {
    return cinema;
  }

  public void setCinema(Cinema cinema) {
    if (cinema == null) {
      throw new IllegalArgumentException("Cinema object should not be set NULL.");
    }
    if (this.cinema != null) {
      this.cinema.removeCashOffice(this);
    }
    if ( !cinema.existCashOffice(this)) {
      cinema.addCashOffice(this);
    }
    this.cinema = cinema;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    if (number <= 0) {
      throw new IllegalArgumentException("Number should be bigger than zero.");
    }
    this.number = number;
  }

  public Iterator<Ticket> showTikets() {
    return tickets.iterator();
  }

  public void addTicket(Ticket ticket) {
    if (ticket == null) {
      throw new NullObjectInsertionException("null ticket should not be added");
    }
    if (exists(ticket)) {
      throw new DuplicateInsertionException("This Ticket already added to CashOffice. Could not be added second time");
    }
    tickets.add(ticket);
    ticket.setCashOffice(this);

  }

  public boolean exists(Ticket ticket) {
    return tickets.contains(ticket);
  }

  public void saleTicket(Ticket ticket) {
    if (ticket == null) {
      throw new CashOfficeManipulationException("Cannot sale NULL ticket");
    }
    if (ticket.getStatus() == TicketStatus.NOT_FOR_SALE || ticket.getStatus() == TicketStatus.SOLD) {
      throw new CashOfficeManipulationException("Cannot sale ticket with status 'NOT FOR SALE' or 'SOLD'");
    }
    ticket.changeStatus(TicketStatus.SOLD);
    if ( !exists(ticket)) {
      addTicket(ticket);
    }

  }

  public void reserveTicket(Ticket ticket) {
    if (ticket == null) {
      throw new CashOfficeManipulationException("Cannot reserve NULL ticket");
    }
    if (ticket.getStatus() != TicketStatus.ENABLE) {
      throw new CashOfficeManipulationException("Cannot reserve ticket with status != ENABLE");
    }
    ticket.changeStatus(TicketStatus.RESERVED);
    if ( !exists(ticket)) {
      addTicket(ticket);
    }
  }

  public void returnTicket(Ticket ticket) {
    if (ticket == null) {
      throw new CashOfficeManipulationException("Cannot reserve NULL ticket");
    }
    if (ticket.getStatus() != TicketStatus.SOLD) {
      throw new CashOfficeManipulationException("Cannot return ticket which not SOLD");
    }
    switch (ticket.getSeance().getStatus()) {
      case OPENED:
        ticket.changeStatus(TicketStatus.ENABLE);
        break;
      case CANCELED:
        ticket.changeStatus(TicketStatus.NOT_FOR_SALE);
        break;
      case CLOSED:
        throw new CashOfficeManipulationException("Cannot return ticket for seance which already ended");
    }

    if ( !exists(ticket)) {
      addTicket(ticket);
    }
  }

  public List<Seance> getSeances() {
    List<Seance> seances = new ArrayList<Seance>();
    Seance tempSeance;
    int tempIndex;
    for (Ticket ticket : tickets) {
      System.out.println(ticket.getPlace().getNumber());
      tempSeance = ticket.getSeance();
      tempIndex = seances.indexOf(tempSeance);
      if (tempIndex == -1) {
        seances.add(tempSeance);
      } else {
        seances.get(tempIndex).addTicket(ticket);
      }
    }
    return seances;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other == this)
      return true;
    CashOffice otherCashOffice = (CashOffice) other;
    return this.number == otherCashOffice.number;
  }

}
