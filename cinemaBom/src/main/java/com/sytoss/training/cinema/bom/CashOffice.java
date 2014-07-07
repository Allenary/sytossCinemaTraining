package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bom.exception.NullObjectInsertionException;

public class CashOffice {

  private int number;

  private Cinema cinema;

  private List<Ticket> tickets;

  public CashOffice(int number) {
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
    if ( !cinema.exists(this)) {
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
    tickets.add(ticket);

  }

  public boolean exists(Ticket ticket) {
    return tickets.contains(ticket);
  }
}
