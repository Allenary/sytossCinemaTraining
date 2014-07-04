package com.sytoss.training.cinema.bom;

public class CashOffice {

  private int number;

  private Cinema cinema;

  public CashOffice(int number) {
    this.number = number;
  }

  public CashOffice() {
  }

  public Cinema getCinema() {
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

  public void removeCinema() {
    if (cinema != null) {
      if (cinema.exists(this)) {
        cinema.removeCashOffice(this);
      }
      cinema = null;
    }
  }
}
