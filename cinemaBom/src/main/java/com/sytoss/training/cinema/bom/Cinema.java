package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Cinema {

  // TODO: implement dependencies with 1-to-many multiplicity
  private String name;

  private String address;

  private List<Movie> movies;

  private List<CashOffice> cashoffices;

  public Cinema() {
    movies = new ArrayList<Movie>();
    cashoffices = new ArrayList<CashOffice>();
  }

  public Cinema(String name) {
    this();
    this.name = name;
  }

  public List<Movie> showAllMovies() {
    return movies;
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
      throw new IllegalArgumentException("Null CashOffice cannot be added");
    }
    if (exists(cashOffice)) {
      throw new IllegalArgumentException("This cashOffice already added");
    }
    if (cashOffice.getCinema() == null) {
      cashOffice.setCinema(this);
    } else if ( !cashOffice.getCinema().equals(this)) {
      throw new IllegalArgumentException("it's cashoffice of another Cinema");
    }

    cashoffices.add(cashOffice);

  }

  public boolean exists(CashOffice searchedCashOffice) {
    for (CashOffice cashoffice : cashoffices) {
      if (searchedCashOffice.equals(cashoffice))
        return true;
    }
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      System.out.print("obj is null");
      return false;
    }
    if ( !(obj instanceof Cinema)) {
      System.out.print("not instance of cinema");
      return false;
    }

    Cinema comparableCinema = (Cinema) obj;
    boolean isEqual = (comparableCinema.name == this.name) && (comparableCinema.address == this.address);
    System.out.print("EQ=" + isEqual);
    return isEqual;

  }
}
