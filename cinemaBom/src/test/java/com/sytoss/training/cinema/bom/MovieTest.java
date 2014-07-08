package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sytoss.training.cinema.bom.Movie;

public class MovieTest {

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForZeroDuration() {
    Movie movie = new Movie("Hoght");
    movie.setDuration(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNegativeDuration() {
    Movie movie = new Movie("Hoght");
    movie.setDuration( -8);
  }

  @Test
  public void shouldSpecifyDuration() {
    Movie movie = new Movie("Hoght");
    movie.setDuration(120);
    assertEquals(120, movie.getDuration());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForEmptyName() {
    Movie movie = new Movie("Hoght");
    movie.setName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNullName() {
    Movie movie = new Movie("Hoght");
    movie.setName(null);
  }

  @Test
  public void shouldSpecifyName() {
    Movie movie = new Movie("Hoght");
    movie.setName("Titanic");
    assertEquals("Titanic", movie.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionForNullDescription() {
    Movie movie = new Movie("Hoght");
    movie.setDescription(null);
  }

  public void shouldSetValidDescription() {
    Movie movie = new Movie("Hoght");
    movie.setDescription("Disaster movie of 1997 year");
    assertEquals("Disaster movie of 1997 year", movie.getDescription());
  }
}
