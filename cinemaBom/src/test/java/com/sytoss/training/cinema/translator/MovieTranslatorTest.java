package com.sytoss.training.cinema.translator;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.junit.Test;

import com.sytoss.training.cinema.bom.Movie;

public class MovieTranslatorTest {

  @Test
  public void shouldTranslateToDTO() {
    String movieDTO = new MovieTranslator().toDTO(new Movie("Titanik"));
    assertEquals("Titanik", movieDTO);
  }

  @Test
  public void shouldTranslsteFromDTO() {
    Movie movie = new MovieTranslator().fromDTO("Terminator");
    assertEquals("Terminator", movie.getName());
  }

  @Test
  public void shouldTranslateToElement() {
    Movie movie = new Movie("TOR");
    Element testElement = new MovieTranslator().toElement(movie);
    assertEquals("movie", testElement.getName());
    assertEquals("TOR", testElement.getText());
  }
}
