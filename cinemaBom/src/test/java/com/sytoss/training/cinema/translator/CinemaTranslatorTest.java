package com.sytoss.training.cinema.translator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sytoss.training.cinema.bom.Cinema;

public class CinemaTranslatorTest {

  @Test
  public void shouldTranslateToDTO() {
    String cinemaDTO = new CinemaTranslator().toDTO(new Cinema("Kronverk"));
    assertEquals("Kronverk", cinemaDTO);
  }

  @Test
  public void shouldTranslateFromDTO() {
    Cinema cinema = new CinemaTranslator().fromDTO("Park");
    assertEquals("Park", cinema.showName());
  }

}
