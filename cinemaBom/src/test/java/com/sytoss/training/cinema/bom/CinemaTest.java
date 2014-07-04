package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CinemaTest {

  @Test
  public void shouldAddValidCashoffice() {

    //    TODO: add verification cinema is added in cashoffice
    Cinema cinema = new Cinema();
    CashOffice cashoffice = new CashOffice(56);
    cinema.addCashOffice(cashoffice);
    assertTrue(cinema.exists(cashoffice));
    boolean isEqual = cinema.equals(cashoffice.getCinema());
    System.out.print(isEqual);
    assertTrue(isEqual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseErrorForAddingCashOfficeAssignedToAnotherCinema() {
    Cinema cinema = new Cinema("Planeta Kino");
    CashOffice cashOffice = new CashOffice(13);
    cashOffice.setCinema(new Cinema("NoNameCinema"));
    cinema.addCashOffice(cashOffice);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldPaiseErrorForAddingCashOfficeWhichAlreadyAdded() {
    Cinema cinema = new Cinema();
    CashOffice cashOffice = new CashOffice(17);
    cinema.addCashOffice(cashOffice);
    cinema.addCashOffice(cashOffice);
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
    assertTrue(cinema.showAllMovies().contains(movie));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullMovieInstance() {
    Cinema cinema = new Cinema();
    cinema.addMovie(null);
  }

}
