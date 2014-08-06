package com.sytoss.training.cinema.bom;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.sytoss.training.cinema.exception.DuplicateInsertionException;
import com.sytoss.training.cinema.exception.NullObjectInsertionException;

public class Cinema {

  private String name;

  private String address;

  private List<Movie> movies;

  private List<Room> rooms;

  private List<CashOffice> cashOffices;

  private List<Seance> seances;

  public Cinema() {
    movies = new ArrayList<Movie>();
    rooms = new ArrayList<Room>();
    cashOffices = new ArrayList<CashOffice>();
    seances = new ArrayList<Seance>();
  }

  public Cinema(String name) {
    this();
    this.name = name;
  }

  public Iterator<CashOffice> cashOfficeIterator() {
    return cashOffices.iterator();
  }

  public void addMovie(Movie movie) {
    if (movie == null) {
      throw new NullObjectInsertionException("Movie shouldn't be NULL.");
    }
    if (existMovie(movie)) {
      throw new DuplicateInsertionException();
    }
    movies.add(movie);
  }

  public Iterator<Room> roomIterator() {
    return rooms.iterator();
  }

  public Iterator<Movie> movieIterator() {
    return movies.iterator();
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    if (StringUtils.isBlank(address)) {
      throw new IllegalArgumentException("Address shouldn't be blank, NULL or empty.");
    }
    this.address = address;
  }

  public void setName(String name) {
    if (StringUtils.isBlank(name)) {
      throw new IllegalArgumentException("Name shouldn't be NULL or empty.");
    }
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void addCashOffice(CashOffice cashOffice) {
    if (cashOffice == null) {
      throw new NullObjectInsertionException("Null CashOffice cannot be added");
    }

    if ( !existCashOffice(cashOffice) && !hasCashOfficeWithSameNumber(cashOffice)) {
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

  public boolean existCashOffice(CashOffice searchedCashOffice) {
    return cashOffices.contains(searchedCashOffice);
  }

  public boolean existSeance(Seance seance) {
    return seances.contains(seance);
  }

  public Iterator<Seance> getSeances() {
    return seances.iterator();
  }

  public void removeCashOffice(CashOffice cashOffice) {
    cashOffices.remove(cashOffice);
  }

  public void addRoom(Room room) {
    if (room == null) {
      throw new NullObjectInsertionException();
    }

    if (rooms.contains(room)) {
      throw new DuplicateInsertionException();
    }
    rooms.add(room);
  }

  public boolean existRoom(Room room) {
    return rooms.contains(room);
  }

  public void addSeance(Seance seance) {
    if (seance == null) {
      throw new NullObjectInsertionException();
    }
    if (existSeance(seance)) {
      throw new DuplicateInsertionException("this seance already added to sinema. Cannot be added second time.");
    }
    seances.add(seance);
  }

  public boolean existMovie(Movie movie) {
    return movies.contains(movie);
  }

  public Iterator<Seance> seanceIterator() {
    return seances.iterator();
  }

  public Iterator<Seance> showNextSeances() {
    List<Seance> nextSeances = new ArrayList<Seance>();
    Calendar now = Calendar.getInstance();
    for (Seance seance : seances) {
      if (seance.getStartDateTime().after(now)) {
        nextSeances.add(seance);
      }
    }
    Collections.sort(nextSeances, new SeanceComparatorByDate());
    return nextSeances.iterator();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other == this)
      return true;
    Cinema otherCinema = (Cinema) other;
    return this.name.equals(otherCinema.name);
  }
}
