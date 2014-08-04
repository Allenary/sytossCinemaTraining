package com.sytoss.training.cinema.translator;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.junit.Test;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Movie;
import com.sytoss.training.cinema.bom.Place;
import com.sytoss.training.cinema.bom.Room;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;

public class CashOfficeTranslatorTest {

  @Test
  public void shouldTranslateToDTO() {
    String cashOfficeDTO = new CashOfficeTranslator().toDTO(new CashOffice(8));
    assertEquals("8", cashOfficeDTO);
  }

  @Test
  public void shouldTranslateFromDTO() {
    CashOffice cashOffice = new CashOfficeTranslator().fromDTO("1");
    assertEquals(1, cashOffice.getNumber());
  }

  @Test
  public void shouldTranslateToElement() throws DataConversionException {
    CashOffice cashOffice = new CashOffice(1);

    Seance seance_1 = new Seance(new Room("blue"), new GregorianCalendar(2014, Calendar.APRIL, 21, 20, 30, 0));
    seance_1.setMovie(new Movie("Star Wars"));

    Ticket ticket_1 = new Ticket(new Place(7, new Row(3)), 20.0);
    ticket_1.setSeance(seance_1);
    ticket_1.setCashOffice(cashOffice);

    Ticket ticket_2 = new Ticket(new Place(6, new Row(3)), 20.0);
    ticket_2.setSeance(seance_1);
    ticket_2.setCashOffice(cashOffice);

    Seance seance_2 = new Seance(new Room("red"), new GregorianCalendar(2014, Calendar.APRIL, 22, 20, 30, 0));
    seance_2.setMovie(new Movie("Tor"));

    Ticket ticket_3 = new Ticket(new Place(5, new Row(3)), 20.0);
    ticket_3.setSeance(seance_2);
    ticket_3.setCashOffice(cashOffice);

    Element element = new CashOfficeTranslator().toElement(cashOffice);

    assertEquals(1, element.getAttribute("id").getIntValue());
    List<Element> seances = element.getChildren("seance");
    assertEquals(2, seances.size());
    assertEquals("blue", seances.get(0).getChild("room").getValue());
    assertEquals("Star Wars", seances.get(0).getChild("movie").getValue());
    assertEquals(2, seances.get(0).getChild("tickets").getChildren().size());
    assertEquals(7, seances.get(0).getChild("tickets").getChildren().get(0).getAttribute("place").getIntValue());
    assertEquals(3, seances.get(0).getChild("tickets").getChildren().get(0).getAttribute("row").getIntValue());
    assertEquals(20.0, seances.get(0).getChild("tickets").getChildren().get(0).getAttribute("price").getDoubleValue(), 0);

    assertEquals(6, seances.get(0).getChild("tickets").getChildren().get(1).getAttribute("place").getIntValue());
    assertEquals(3, seances.get(0).getChild("tickets").getChildren().get(1).getAttribute("row").getIntValue());
    assertEquals(20.0, seances.get(0).getChild("tickets").getChildren().get(1).getAttribute("price").getDoubleValue(), 0);

    assertEquals("red", seances.get(1).getChild("room").getValue());
    assertEquals("Tor", seances.get(1).getChild("movie").getValue());
    assertEquals(1, seances.get(1).getChild("tickets").getChildren().size());
    assertEquals(5, seances.get(1).getChild("tickets").getChildren().get(0).getAttribute("place").getIntValue());
    assertEquals(3, seances.get(1).getChild("tickets").getChildren().get(0).getAttribute("row").getIntValue());
    assertEquals(20.0, seances.get(1).getChild("tickets").getChildren().get(0).getAttribute("price").getDoubleValue(), 0);
  }

}
