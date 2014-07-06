package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bom.exception.NullObjectInsertionException;

public class CinemaTest {

  @Test
  public void shouldAddValidCashoffice() {

    Cinema cinema = new Cinema("Kronverk");
    CashOffice cashoffice = new CashOffice(56);
    cinema.addCashOffice(cashoffice);
    assertTrue(cinema.exists(cashoffice));
    assertEquals(cinema, cashoffice.getCinema());
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseExceptionForAddingNullCashOffice() {
    Cinema cinema = new Cinema();
    cinema.addCashOffice(null);
  }

  @Test
  public void shouldNotAddDuplicateCashOffice() {
    Cinema cinema = new Cinema();
    CashOffice cashOffice = new CashOffice(1);
    cinema.addCashOffice(cashOffice);
    cinema.addCashOffice(cashOffice);
    assertEquals(1, cinema.countCashOffices());
  }

  @Test
  public void shouldNotAddDuplicateNumberCashOffice() {
    Cinema cinema = new Cinema();
    cinema.addCashOffice(new CashOffice(1));
    cinema.addCashOffice(new CashOffice(1));
    assertEquals(1, cinema.countCashOffices());
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
    assertEquals(newCinema, cashOffice.getCinema());
  }

  @Test
  public void shouldRemoveCashoffice() {
    Cinema cinema = new Cinema();
    CashOffice cashOffice = new CashOffice(23);
    cinema.addCashOffice(cashOffice);
    cinema.removeCashOffice(cashOffice);
    assertFalse(cinema.exists(cashOffice));
    assertEquals(null, cashOffice.getCinema());
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
    assertEquals("Kronverk", cinema.getName());
    assertEquals("Krasnoproletarskaya st., 16/2, Ent. 5, Moscow, 127473, Russian Federation", cinema.getAddress());
  }

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
  public void shouldSpecifyMovieInstance() {
    Movie movie = new Movie();
    Cinema cinema = new Cinema();
    movie.setName("Brave heart");
    cinema.addMovie(movie);

  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullMovieInstance() {
    Cinema cinema = new Cinema();
    cinema.addMovie(null);
  }

}
