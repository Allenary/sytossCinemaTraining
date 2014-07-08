package com.sytoss.training.cinema.bom;

import bom.exception.NullObjectInsertionException;
import bom.exception.ReassignObjectException;

public class Ticket {

  private double price;

  private TicketStatus status;

  private Place place;

  private Seance seance;

  public Ticket() {
    status = TicketStatus.ENABLE;
  }

  public Ticket(Place place) {
    this();
    setPlace(place);
  }

  //  @Override
  //  public boolean equals(Object other) {
  //    if (other == null)
  //      return false;
  //    if (other == this)
  //      return true;
  //    if ( !(other instanceof Ticket))
  //      return false;
  //    Ticket otherTicket = (Ticket) other;
  //    return (this.seance.equals(otherTicket.getSeance()) && this.place.equals(otherTicket.getPlace()));
  //
  //  }

  public Seance getSeance() {
    return seance;
  }

  public void setSeance(Seance seance) {
    if (seance == null) {
      throw new NullObjectInsertionException("Seance shouldn't be NULL.");
    }
    if (this.seance != null && this.seance != seance) {
      throw new ReassignObjectException("Ticket already assigned to another seance. Ticket could not be reassigned.");
    }

    if (this.seance == null) {
      this.seance = seance;
      if ( !seance.contains(this)) {
        seance.addTicket(this);
      }
    }
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    if (price <= 0) {
      throw new IllegalArgumentException("Price should be bigger than zero");
    }
    this.price = price;
  }

  public TicketStatus getStatus() {
    return status;
  }

  public void changeStatus(TicketStatus status) {
    if (status == null) {
      throw new IllegalArgumentException("Status shouldn't be NULL.");
    }
    this.status = status;
  }

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    if (place == null) {
      throw new IllegalArgumentException("Place shouldn't be NULL.");
    }
    this.place = place;
  }

}
