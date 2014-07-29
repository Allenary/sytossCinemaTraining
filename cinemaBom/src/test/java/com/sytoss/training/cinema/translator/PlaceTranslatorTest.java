package com.sytoss.training.cinema.translator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sytoss.training.cinema.bom.Place;

public class PlaceTranslatorTest {

  @Test
  public void shouldTranslateToDTO() {
    String placeDTO = new PlaceTranslator().toDTO(new Place(7));
    assertEquals("7", placeDTO);
  }

  @Test
  public void shouldTranslateFromDTO() {
    Place place = new PlaceTranslator().fromDTO("3");
    assertEquals(3, place.getNumber());
  }
}
