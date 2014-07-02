package com.sytoss.training.cinema.cinemaBom;

public class Ticket {

  private double price;

  private TicketStatuses status;

  private Place place;

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    if (price <= 0) {
      throw new IllegalArgumentException();
    }
    this.price = price;
  }

  public TicketStatuses getStatus() {
    return status;
  }

  public void setStatus(TicketStatuses status) {
    if (status == null) {
      throw new IllegalArgumentException();
    }
    this.status = status;
  }

  public Place getPlace() {
    return place;
  }

  public void setPlace(Place place) {
    if (place == null) {
      throw new IllegalArgumentException();
    }
    this.place = place;
  }

}
