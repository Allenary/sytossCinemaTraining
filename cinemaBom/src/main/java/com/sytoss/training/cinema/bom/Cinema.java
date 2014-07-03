package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.List;

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
    if (address == null || address.isEmpty()) {
      throw new IllegalArgumentException("Address shouldn't be NULL or empty.");
    } else {
      this.address = address;
    }
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name shouldn't be NULL or empty.");
    } else {
      this.name = name;
    }
  }

  public String getName() {
    return name;
  }

  public void addCashOffice(CashOffice cashOffice) {
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
    if (obj == null)
      return false;
    if ( !(obj instanceof CashOffice))
      return false;
    Cinema comparableCinema = (Cinema) obj;
    return (comparableCinema.name == this.name) && (comparableCinema.address == this.address);

  }
}
