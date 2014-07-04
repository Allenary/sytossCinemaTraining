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

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if ( !(obj instanceof CashOffice))
      return false;
    CashOffice comparableCashOffice = (CashOffice) obj;
    return comparableCashOffice.number == this.number;

  }

  public boolean exists(Cinema searchedCinema) {
    if (cinema == null)
      return false;
    return cinema.equals(searchedCinema);

  }

  public void removeCinema() {
    if (cinema != null) {
      cinema.removeCashOffice(this);
      cinema = null;
    }
  }
}
