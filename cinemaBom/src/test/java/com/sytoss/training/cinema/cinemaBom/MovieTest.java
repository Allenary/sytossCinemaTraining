package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MovieTest {

  static Movie movie;

  @BeforeClass
  public static void initializeMovie() {
    movie = new Movie();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetDurationIsBiggerThanZero() {
    movie.setDuration(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetDurationIsNegative() {
    movie.setDuration(-8);
  }

  @Test
  public void testSetDurrationCorrect() {
    int movieDur = 120;
    movie.setDuration(120);
    assertEquals(movie.getDuration(), movieDur);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNameIsEmpty() {
    movie.setName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNameIsNull() {
    movie.setName(null);
  }

  @Test
  public void testSetNameCorrect() {
    String movieName = "Titanic";
    movie.setName(movieName);
    assertEquals(movie.getName(), movieName);
  }
}
