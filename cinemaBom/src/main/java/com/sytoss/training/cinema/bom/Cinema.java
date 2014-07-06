package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bom.exception.NullObjectInsertionException;

public class Cinema {

  // TODO: implement dependencies with 1-to-many multiplicity
  private String name;

  private String address;

  private List<Movie> movies;

  private List<CashOffice> cashOffices;

  public Cinema() {
    movies = new ArrayList<Movie>();
    cashOffices = new ArrayList<CashOffice>();
  }

  public Cinema(String name) {
    this();
    this.name = name;
  }

  //not used!!
  public Iterator<CashOffice> showAllCashOffices() {
    return cashOffices.iterator();
  }

  public int countCashOffices() {
    return cashOffices.size();
  }

  public void addMovie(Movie movie) {
    if (movie == null) {
      throw new IllegalArgumentException("Movie shouldn't be NULL.");
    }
    movies.add(movie);
  }

  public boolean removeMovie(Movie movie) {
    return movies.remove(movie);

  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    if (StringUtils.isBlank(address)) {
      throw new IllegalArgumentException("Address shouldn't be blank, NULL or empty.");
    } else {
      this.address = address;
    }
  }

  public void setName(String name) {
    if (StringUtils.isBlank(name)) {
      throw new IllegalArgumentException("Name shouldn't be NULL or empty.");
    } else {
      this.name = name;
    }
  }

  public String getName() {
    return name;
  }

  public void addCashOffice(CashOffice cashOffice) {
    if (cashOffice == null) {
      throw new NullObjectInsertionException("Null CashOffice cannot be added");
    }
    if ( !exists(cashOffice) && ( !hasCashOfficeWithSameNumber(cashOffice))) {
      cashOffices.add(cashOffice);
      cashOffice.setCinema(this);
    }

  }

  private boolean hasCashOfficeWithSameNumber(CashOffice searchedCashOffice) {
    for (CashOffice cashOffice : cashOffices) {
      if (searchedCashOffice.getNumber() == cashOffice.getNumber())
        return true;
    }
    return false;
  }

  public boolean exists(CashOffice searchedCashOffice) {
    for (CashOffice cashoffice : cashOffices) {
      if (searchedCashOffice.equals(cashoffice))
        return true;
    }
    return false;
  }

  public void removeCashOffice(CashOffice cashOffice) {
    cashOffices.remove(cashOffice);
    if (cashOffice.getCinema() != null) {
      cashOffice.removeCinema();
    }
  }
}
