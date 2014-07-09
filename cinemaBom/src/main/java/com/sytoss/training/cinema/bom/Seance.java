package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import bom.exception.DuplicateInsertionException;
import bom.exception.NullObjectInsertionException;
import bom.exception.ReassignObjectException;

public class Seance {

  private Calendar startDateTime;

  private SeanceStatus status;

  private Room room;

  private List<Ticket> tickets;

  private Movie movie;

  public Seance() {
    tickets = new ArrayList<Ticket>();
    status = SeanceStatus.OPENED;
  }

  public Seance(Room room, Calendar startDateTime) {
    this();
    setRoom(room);
    setStartDateTime(startDateTime);
  }

  public Iterator<Ticket> getTickets() {
    return tickets.iterator();
  }

  public void setStartDateTime(Calendar calendar) {
    if (calendar == null) {
      throw new IllegalArgumentException("StartDateTime shouldn't be NULL.");
    }
    this.startDateTime = calendar;
  }

  public Object getStartDateTime() {
    return startDateTime;
  }

  public void setStatus(SeanceStatus status) {
    if (status == null) {
      throw new IllegalArgumentException("Status shouldn't be NULL");
    }
    this.status = status;
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

  public boolean existsTicket(Ticket ticket) {
    return tickets.contains(ticket);
  }

  public void addTicket(Ticket ticket) {
    if (ticket == null) {
      throw new NullObjectInsertionException("null ticket shouldn't be added");
    }

    if (ticket.getSeance() != this && ticket.getSeance() != null) {
      throw new ReassignObjectException("Ticket already assigned to seance. Ticket could not be reassigned to another seance!");
    }

    if (tickets.contains(ticket)) {
      throw new DuplicateInsertionException("Seance already has same ticket");
    }
    tickets.add(ticket);
    ticket.setSeance(this);
  }

  public boolean hasTicketOnPlace(Place place) {
    for (Ticket ticket : tickets) {
      if (ticket.getPlace().equals(place)) {
        return true;
      }
    }
    return false;
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

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other == this)
      return true;
    if ( !(other instanceof Seance))
      return false;
    Seance otherSeance = (Seance) other;
    return (this.startDateTime.equals(otherSeance.getStartDateTime()) && (this.room.equals(otherSeance.getRoom())));

  }
}
