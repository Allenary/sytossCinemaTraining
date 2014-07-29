package com.sytoss.training.cinema.bom;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Iterator;

import org.junit.Test;

import com.sytoss.training.cinema.exception.DuplicateInsertionException;
import com.sytoss.training.cinema.exception.NullObjectInsertionException;

public class CinemaTest {

  @Test
  public void shouldAddValidSeance() {
    Cinema cinema = new Cinema();
    Seance seance = new Seance();
    cinema.addSeance(seance);
    Iterator<Seance> seances = cinema.showSeances();
    assertTrue(seances.hasNext());
    seances.next();
    assertFalse(seances.hasNext());
  }

  @Test
  public void shouldReturnTrueWhileSeanceExists() {
    Cinema cinema = new Cinema();
    Seance seance = new Seance();
    cinema.addSeance(seance);
    assertTrue(cinema.existSeance(seance));
  }

  @Test
  public void shouldReturnFalseWhileSeanceNotExist() {
    assertFalse(new Cinema().existSeance(new Seance()));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForNullSeance() {
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
    assertFalse(cinema.existCashOffice(cashoffice));
    cinema.addCashOffice(cashoffice);
    assertTrue(cinema.existCashOffice(cashoffice));
    assertEquals(cinema, cashoffice.showCinema());
    Iterator<CashOffice> allCashOffices = cinema.showCashOffices();
    assertTrue(allCashOffices.hasNext());
    allCashOffices.next();
    assertFalse(allCashOffices.hasNext());
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
    Iterator<CashOffice> allCashOffices = cinema.showCashOffices();
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
    assertTrue(newCinema.existCashOffice(cashOffice));
    assertFalse(oldCinema.existCashOffice(cashOffice));
    assertEquals(newCinema, cashOffice.showCinema());
  }

  @Test
  public void shouldAddValidRoom() {
    Cinema cinema = new Cinema();
    Room room = new Room("red");
    cinema.addRoom(room);
    Iterator<Room> rooms = cinema.showAllRooms();
    assertTrue(rooms.hasNext());
    rooms.next();
    assertFalse(rooms.hasNext());
  }

  @Test
  public void shouldVerifyExistanceOfRoom() {
    Cinema cinema = new Cinema();
    Room room = new Room("red");
    assertFalse(cinema.existRoom(room));
    cinema.addRoom(room);
    assertTrue(cinema.existRoom(room));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForAddNullRoom() {
    new Cinema().addRoom(null);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseExeptionOnAddDuplicateRoom() {
    Cinema cinema = new Cinema();
    Room room = new Room("a");
    cinema.addRoom(room);
    cinema.addRoom(room);
  }

  // Name field test cover
  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForEmptyName() {
    Cinema cinema = new Cinema();
    cinema.setName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNullName() {
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
  public void shouldRaiseExceptionForEmptyAddress() {
    new Cinema().setAddress("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNullAddress() {
    Cinema cinema = new Cinema();
    cinema.setAddress(null);
  }

  @Test
  public void shouldAddValidMovie() {
    Movie movie = new Movie("Hoght");
    Cinema cinema = new Cinema();
    movie.setName("Brave heart");
    cinema.addMovie(movie);
    Iterator<Movie> movies = cinema.showPoster();
    assertTrue(movies.hasNext());
    movies.next();
    assertFalse(movies.hasNext());
  }

  @Test
  public void shouldReturnTrueIfMovieExists() {
    Movie movie = new Movie("Hoght");
    Cinema cinema = new Cinema();
    cinema.addMovie(movie);
    assertTrue(cinema.existMovie(movie));

  }

  @Test
  public void shouldReturnFalseIfMovieNotExists() {
    assertFalse(new Cinema().existMovie(new Movie("Hoght")));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForNullMovie() {
    Cinema cinema = new Cinema();
    cinema.addMovie(null);
  }

  @Test(expected = DuplicateInsertionException.class)
  public void shouldRaiseExceptionForAddDuplicateMovie() {
    Movie movie = new Movie("Hoght");
    Cinema cinema = new Cinema();
    cinema.addMovie(movie);
    cinema.addMovie(movie);
  }

  @Test
  public void shouldShowNextSeances() {
    Cinema cinema = new Cinema();

    Calendar pastDate = Calendar.getInstance();
    Calendar futureDate = Calendar.getInstance();
    Calendar futurestDate = Calendar.getInstance();

    Seance pastSeance = new Seance();
    Seance firstFutureSeance = new Seance();
    Seance secondFutureSeance = new Seance();

    pastDate.add(Calendar.HOUR_OF_DAY, -1);
    pastSeance.setStartDateTime(pastDate);
    futureDate.add(Calendar.HOUR_OF_DAY, +2);
    secondFutureSeance.setStartDateTime(futurestDate);
    futurestDate.add(Calendar.HOUR_OF_DAY, +8);
    firstFutureSeance.setStartDateTime(futureDate);
    cinema.addSeance(firstFutureSeance);
    cinema.addSeance(pastSeance);
    cinema.addSeance(secondFutureSeance);
    Iterator<Seance> nextSeances = cinema.showNextSeances();

    assertEquals(firstFutureSeance, nextSeances.next());
    assertEquals(secondFutureSeance, nextSeances.next());
    assertFalse(nextSeances.hasNext());
  }
}
