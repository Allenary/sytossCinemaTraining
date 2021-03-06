package com.sytoss.training.cinema.translator;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.junit.Test;

import com.sytoss.training.cinema.bom.Movie;
import com.sytoss.training.cinema.bom.Place;
import com.sytoss.training.cinema.bom.Room;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.exception.SeanceNotFullException;

public class SeanceTranslatorTest {

  @Test
  public void shouldTranslateToDTO() {
    Seance seance = new Seance();
    Calendar date = Calendar.getInstance();
    date.set(2014, Calendar.SEPTEMBER, 20, 10, 45);
    seance.setStartDateTime(date);

    String seanceDTO = new SeanceTranslator().toDTO(seance);

    assertEquals("20.09.2014 10:45", seanceDTO);
  }

  @Test
  public void shouldTranslateFromDTO() throws ParseException {
    Calendar date = Calendar.getInstance();
    date.set(2014, Calendar.SEPTEMBER, 20, 10, 45);

    Seance seance = new SeanceTranslator().fromDTO("20.09.2014 10:45");
    assertEquals(date.get(Calendar.YEAR), seance.getStartDateTime().get(Calendar.YEAR));
    assertEquals(date.get(Calendar.MONTH), seance.getStartDateTime().get(Calendar.MONTH));
    assertEquals(date.get(Calendar.DAY_OF_MONTH), seance.getStartDateTime().get(Calendar.DAY_OF_MONTH));
    assertEquals(date.get(Calendar.HOUR_OF_DAY), seance.getStartDateTime().get(Calendar.HOUR_OF_DAY));
    assertEquals(date.get(Calendar.MINUTE), seance.getStartDateTime().get(Calendar.MINUTE));
  }

  @Test
  public void shouldTranslateToElement() throws DataConversionException {
    Seance seance = new Seance(new Room("blue"), new GregorianCalendar(2014, Calendar.APRIL, 21, 20, 30, 0));
    seance.setMovie(new Movie("Star Wars"));

    Element seanceElement = new SeanceTranslator().toElement(seance);

    assertEquals("blue", seanceElement.getChild("room").getText());
    assertEquals("Star Wars", seanceElement.getChild("movie").getText());
    assertEquals("2014-04-21T20:30:00Z", seanceElement.getAttribute("startDateTime").getValue());

  }

  @Test
  public void shouldRaiseErrorWhenNoTicketis() {
    Seance seance = new Seance(new Room("blue"), new GregorianCalendar(2014, Calendar.APRIL, 21, 20, 30, 0));
    seance.setMovie(new Movie("Star Wars"));

    Element seanceElement = new SeanceTranslator().toElement(seance);
    assertEquals("blue", seanceElement.getChild("room").getText());
    assertEquals("Star Wars", seanceElement.getChild("movie").getText());
    assertEquals("2014-04-21T20:30:00Z", seanceElement.getAttribute("startDateTime").getValue());
  }

  @Test(expected = SeanceNotFullException.class)
  public void shouldRaiseErrorWhenSeanceHasOnlyTickets() {
    Seance seance = new Seance();
    seance.addTicket(new Ticket(new Place(2, new Row(1)), 59.50));

    new SeanceTranslator().toElement(seance);

  }

  @Test
  public void shouldTranslatorFromDTOElement() throws DataConversionException, ParseException {
    Element seanceElement = new Element("seance");
    seanceElement.setAttribute("startDateTime", "2014-08-20T10:30:00Z");
    seanceElement.addContent(new Element("movie").setText("Star wars"));
    seanceElement.addContent(new Element("room").setText("red"));

    Seance seance = new SeanceTranslator().fromDTO(seanceElement);

    assertEquals(new GregorianCalendar(2014, Calendar.AUGUST, 20, 10, 30), seance.getStartDateTime());
    assertEquals("red", seance.getRoom().getName());
    assertEquals("Star wars", seance.getMovie().getName());
  }
}
