package com.sytoss.training.cinema.bom;

import bom.exception.NullObjectInsertionException;
import bom.exception.ReassignObjectException;

public class Ticket {

  private double price;

  private TicketStatus status;

  private Place place;

  private Seance seance;

  public Ticket(Place place) {
    setPlace(place);
    status = TicketStatus.ENABLE;
  }

  public Seance getSeance() {
    return seance;
  }

  public void setSeance(Seance seance) {
    if (seance == null) {
      throw new NullObjectInsertionException("Seance shouldn't be NULL.");
    }
    if (this.seance != null) {
      throw new ReassignObjectException("Ticket already assigned to another seance. Ticket could not be reassigned.");
    }
    if ( !seance.existsTicket(this)) {
      seance.addTicket(this);
    }
    this.seance = seance;
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

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other == this)
      return true;
    if (this.seance == null)
      return false;
    Ticket otherTicket = (Ticket) other;
    return this.place.equals(otherTicket.getPlace()) && this.seance.equals(otherTicket.getSeance());
  }
}
