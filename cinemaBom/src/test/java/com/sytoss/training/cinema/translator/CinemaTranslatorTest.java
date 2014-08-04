package com.sytoss.training.cinema.translator;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.junit.Test;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Movie;
import com.sytoss.training.cinema.bom.Place;
import com.sytoss.training.cinema.bom.Room;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;

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

  @Test
  public void shouldTranslateToElement() throws DataConversionException {
    Cinema cinema = new Cinema("IMAX");

    CashOffice cashOffice_1 = new CashOffice(3);
    CashOffice cashOffice_2 = new CashOffice(1);

    Seance seance_1 = new Seance(new Room("blue"), new GregorianCalendar(2014, Calendar.APRIL, 21, 20, 30, 0));
    seance_1.setMovie(new Movie("Star Wars"));

    Ticket ticket_1 = new Ticket(new Place(7, new Row(3)), 20.0);
    ticket_1.setSeance(seance_1);
    ticket_1.setCashOffice(cashOffice_1);

    Ticket ticket_2 = new Ticket(new Place(13, new Row(3)), 20.0);
    ticket_2.setSeance(seance_1);
    ticket_2.setCashOffice(cashOffice_2);
    cinema.addCashOffice(cashOffice_1);
    cinema.addCashOffice(cashOffice_2);

    Element cinemaElement = new CinemaTranslator().toElement(cinema);
    assertEquals(2, cinemaElement.getChildren().size());
    assertEquals("IMAX", cinemaElement.getAttribute("name").getValue());

    assertEquals(3, cinemaElement.getChildren().get(0).getAttribute("id").getIntValue());
    assertEquals(1, cinemaElement.getChildren().get(1).getAttribute("id").getIntValue());
    assertEquals("Star Wars", cinemaElement.getChildren().get(0).getChildren().get(0).getChildren("movie").get(0).getText());
    assertEquals("blue", cinemaElement.getChildren().get(0).getChildren().get(0).getChildren("room").get(0).getText());
    assertEquals(7, cinemaElement
      .getChildren()
      .get(0)
      .getChildren()
      .get(0)
      .getChild("tickets")
      .getChildren()
      .get(0)
      .getAttribute("place")
      .getIntValue());
    assertEquals(3, cinemaElement
      .getChildren()
      .get(0)
      .getChildren()
      .get(0)
      .getChild("tickets")
      .getChildren()
      .get(0)
      .getAttribute("row")
      .getIntValue());
    assertEquals(
      20.0,
      cinemaElement
        .getChildren()
        .get(0)
        .getChildren()
        .get(0)
        .getChild("tickets")
        .getChildren()
        .get(0)
        .getAttribute("price")
        .getDoubleValue(),
      0);

    //List<Element> cashOffices = cinemaElement.getChildren("seance");
    //assertEquals("Star Wars", cashOffices.get(0).getChild("movie").getChildren().get(0).getText());
  }
}
