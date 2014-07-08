package com.sytoss.training.cinema.bom;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Iterator;

import org.junit.Test;

import bom.exception.DuplicateInsertionException;
import bom.exception.NullObjectInsertionException;

public class CinemaTest {

  @Test
  public void shouldAddValidSeance() {
    Cinema cinema = new Cinema();
    Seance seance = new Seance();
    cinema.addSeance(seance);
    assertTrue(cinema.exists(seance));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForNullSeance() {
    new Cinema().addSeance(null);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseExceptionForAddDuplicateSeance() {
    Cinema cinema = new Cinema();
    Seance seance = new Seance();
    cinema.addSeance(seance);
    cinema.addSeance(seance);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseExceptionForAddSeanceWithDateAndRoomWhichAlreadyHasAnotherSeance() {
    Cinema cinema = new Cinema();
    Calendar now = Calendar.getInstance();
    cinema.addSeance(new Seance(new Room("a"), now));
    cinema.addSeance(new Seance(new Room("a"), now));
  }

  // Cinema[1]-[N]CashOffice
  @Test
  public void shouldAddValidCashoffice() {

    Cinema cinema = new Cinema("Kronverk");
    CashOffice cashoffice = new CashOffice(56);
    cinema.addCashOffice(cashoffice);
    assertTrue(cinema.exists(cashoffice));
    assertEquals(cinema, cashoffice.showCinema());
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForAddingNullCashOffice() {
    Cinema cinema = new Cinema();
    cinema.addCashOffice(null);
  }

  @Test
  public void shouldNotAddDuplicateCashOffice() {
    Cinema cinema = new Cinema();
    CashOffice cashOffice = new CashOffice(3);
    cinema.addCashOffice(cashOffice);
    cinema.addCashOffice(cashOffice);
    Iterator<CashOffice> allCashOffices = cinema.showAllCashOffices();
    allCashOffices.next();
    assertEquals(false, allCashOffices.hasNext());
  }

  @Test
  public void shouldAddCashOfficeAssignedToAnotherCinema() {
    Cinema oldCinema = new Cinema("Planeta Kino");
    Cinema newCinema = new Cinema("MegaKino");
    CashOffice cashOffice = new CashOffice(13);
    cashOffice.setCinema(oldCinema);
    newCinema.addCashOffice(cashOffice);
    assertTrue(newCinema.exists(cashOffice));
    assertFalse(oldCinema.exists(cashOffice));
    assertEquals(newCinema, cashOffice.showCinema());
  }

  @Test
  public void shouldAddValidRoom() {

    Cinema cinema = new Cinema();
    Room room = new Room("red");
    cinema.addRoom(room);
    assertTrue(cinema.exists(room));

  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForAddNullRoom() {
    new Cinema().addRoom(null);
  }

  @Test
  public void shouldNotAddDuplicateRoom() {
    Cinema cinema = new Cinema();
    Room room = new Room();
    cinema.addRoom(room);
    cinema.addRoom(room);
    assertTrue(cinema.exists(room));
    Iterator<Room> allRooms = cinema.showAllRooms();
    allRooms.next();
    assertFalse(allRooms.hasNext());
  }

  // Name field test cover
  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForEmptyName() {
    Cinema cinema = new Cinema();
    cinema.setName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullName() {
    Cinema cinema = new Cinema();
    cinema.setName(null);
  }

  @Test
  public void shouldSpecifyNameAndAddress() {
    Cinema cinema = new Cinema();
    cinema.setName("Kronverk");
    cinema.setAddress("Krasnoproletarskaya st., 16/2, Ent. 5, Moscow, 127473, Russian Federation");
    assertEquals("Kronverk", cinema.showName());
    assertEquals("Krasnoproletarskaya st., 16/2, Ent. 5, Moscow, 127473, Russian Federation", cinema.showAddress());
  }

  //  address field cover
  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForEmptyAddress() {
    Cinema cinema = new Cinema();
    cinema.setAddress("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullAddress() {
    Cinema cinema = new Cinema();
    cinema.setAddress(null);
  }

  // Cinema[1] - [N]Movies reference test cover
  // TODO: create method exists for movie to encapsulate changes
  @Test
  public void shouldAddValidMovie() {
    Movie movie = new Movie();
    Cinema cinema = new Cinema();
    movie.setName("Brave heart");
    cinema.addMovie(movie);
    assertTrue(cinema.exists(movie));

  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseAnErrorForNullMovie() {
    Cinema cinema = new Cinema();
    cinema.addMovie(null);
  }

  @Test
  public void shouldRaiseErrorForAddDuplicateMovie() {
    Movie movie = new Movie();
    Cinema cinema = new Cinema();
    cinema.addMovie(movie);
    cinema.addMovie(movie);
    assertTrue(cinema.exists(movie));
    Iterator<Movie> allMovies = cinema.showPoster();
    allMovies.next();
    assertFalse(allMovies.hasNext());
  }

}
