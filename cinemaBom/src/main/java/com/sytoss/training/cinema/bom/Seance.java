package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.sytoss.training.cinema.exception.DuplicateInsertionException;
import com.sytoss.training.cinema.exception.NullObjectInsertionException;
import com.sytoss.training.cinema.exception.ReassignObjectException;
import com.sytoss.training.cinema.exception.SeanceChangeStateException;

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

  public Calendar getStartDateTime() {
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

    if (ticket.getSeance() != null && !ticket.getSeance().equals(this)) {
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

  public Iterator<Ticket> getAvaliableTickets() {
    List<Ticket> avaliableTickets = new ArrayList<Ticket>();
    for (Ticket ticket : tickets) {
      if (ticket.getStatus() == TicketStatus.ENABLE) {
        avaliableTickets.add(ticket);
      }
    }
    return avaliableTickets.iterator();
  }

  public void cancel() {
    if (status != SeanceStatus.OPENED) {
      throw new SeanceChangeStateException("Closed or Canceled seance cannot be caneled");
    }
    setStatus(SeanceStatus.CANCELED);
    for (Ticket ticket : tickets) {
      if (ticket.getStatus() != TicketStatus.SOLD) {
        disableTicket(ticket);
      }
    }
  }

  public void disableTicket(Ticket ticket) {
    if ( !existsTicket(ticket)) {
      throw new IllegalArgumentException("Ticket from another seance cannot be disabled");
    }
    if (ticket.getStatus() != TicketStatus.ENABLE) {
      throw new IllegalArgumentException("Cannot disable ticket which is not ENABLED");
    }
    ticket.changeStatus(TicketStatus.NOT_FOR_SALE);
  }

  public boolean isOpen() {
    return status == SeanceStatus.OPENED;
  }

  public void disableRow(Row row) {
    if (row == null) {
      throw new IllegalArgumentException("cannot disable NULL row");
    }
    if ( !room.equals(row.getRoom())) {
      throw new IllegalArgumentException("cannot disable row from another room.");
    }

    for (Ticket ticket : tickets) {
      if (ticket.getPlace().getRow().equals(row)) {
        disableTicket(ticket);
      }
    }
  }

  public List<Ticket> getTicketsByCOid(int coID) {
    List<Ticket> coTickets = new ArrayList<Ticket>();
    for (Ticket ticket : tickets) {
      if (ticket.getCashOffice().getNumber() == coID) {
        coTickets.add(ticket);
      }
    }
    return coTickets;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other == this)
      return true;
    if (this.getRoom() == null)
      return false;
    if ( !(other instanceof Seance))
      return false;
    Seance otherSeance = (Seance) other;
    if (otherSeance.getRoom() == null)
      return false;
    return (this.startDateTime.equals(otherSeance.getStartDateTime()) && (this.room.equals(otherSeance.getRoom())));

  }
}
