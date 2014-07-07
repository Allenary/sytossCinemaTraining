package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import bom.exception.NullObjectInsertionException;

public class Seance {

  private Calendar startDateTime;

  private SeanceStatus status;

  private Room room;

  private List<Ticket> tickets;

  private Cinema cinema;

  private Movie movie;

  public Seance() {
    tickets = new ArrayList<Ticket>();
    status = SeanceStatus.OPENED;
  }

  public Iterator<Ticket> getTickets() {
    return tickets.iterator();
  }

  public void setStartDateTime(Calendar calendar) {

    Calendar now = Calendar.getInstance();
    if (calendar == (null) || calendar.before(now)) {
      throw new IllegalArgumentException("StartDateTime shouldn't be NULL or past date.");
    }

    this.startDateTime = calendar;
  }

  public Object getStartDateTime() {
    return startDateTime;
  }

  public void setStatus(SeanceStatus status) {

    if (status == null) {
      throw new IllegalArgumentException("Status shouldn't be NULL");
    } else {
      this.status = status;
    }
  }

  public SeanceStatus getStatus() {
    return status;
  }

  public void setRoom(Room room) {
    if (room == null) {
      throw new IllegalArgumentException("Room shouldn't be NULL");
    }
    this.room = room;
  }

  public Room getRoom() {
    return room;
  }

  public boolean contains(Ticket ticket) {
    return tickets.contains(ticket);
  }

  public void addTicket(Ticket ticket) {
    if (ticket == null) {
      throw new NullObjectInsertionException("null ticket shouldn't be added");
    }
    if (ticket.getSeance() == null) {
      ticket.setSeance(this);
    }
    if (ticket.getSeance() == this) {
      tickets.add(ticket);
    }
  }

  public Cinema getCinema() {
    return cinema;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    if (movie == null) {
      throw new NullObjectInsertionException();
    }
    this.movie = movie;
  }

}
