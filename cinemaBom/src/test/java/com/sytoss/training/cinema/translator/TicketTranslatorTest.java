package com.sytoss.training.cinema.translator;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
import com.sytoss.training.cinema.exception.TicketNotFullException;

public class TicketTranslatorTest {

  @Test
  public void shouldTranslateToDTOFullTicket() throws ParseException {
    Place place = new Place(1);
    place.setRow(new Row(2));
    Ticket ticket = new Ticket(place);
    Seance seance = new Seance(new Room("blue"), new GregorianCalendar(2014, Calendar.APRIL, 12, 10, 30));
    seance.setMovie(new Movie("Titanik"));
    ticket.setSeance(seance);
    CashOffice cashOffice = new CashOffice(8);
    cashOffice.setCinema(new Cinema("Kronverk"));
    ticket.setCashOffice(cashOffice);
    ticket.setPrice(59.99);

    String[] ticketDTO = new TicketTranslator().toDTO(ticket);

    assertEquals("Kronverk", ticketDTO[0]);
    assertEquals("blue", ticketDTO[1]);
    assertEquals("Titanik", ticketDTO[2]);
    assertEquals("12.04.2014 10:30", ticketDTO[3]);
    assertEquals("2", ticketDTO[4]);
    assertEquals("1", ticketDTO[5]);
    assertEquals("59.99", ticketDTO[6]);
    assertEquals("8", ticketDTO[7]);
  }

  @Test(expected = ParseException.class)
  public void shouldRaiseExceptionWhenTranslateToDTOTicketWithNoInfo() throws ParseException {
    Ticket ticket = new Ticket();

    new TicketTranslator().toDTO(ticket);
  }

  @Test(expected = ParseException.class)
  public void shouldRaiseExceptionWhenTranslateToDTONotFullTicket() throws ParseException {
    Ticket ticket = new Ticket(new Place(1));
    ticket.setCashOffice(new CashOffice(8));
    Seance seance = new Seance();
    seance.setStartDateTime(new GregorianCalendar(2014, Calendar.APRIL, 12, 10, 30));
    ticket.setSeance(seance);

    new TicketTranslator().toDTO(ticket);
  }

  @Test
  public void shouldTranslateFromDTOFull() throws ParseException {
    Ticket ticket = new TicketTranslator()
      .fromDTO(new String[] {"Kronverk", "blue", "Titanik", "12.04.2014 10:30", "2", "1", "59.99", "8"});

    assertEquals("Kronverk", ticket.getCashOffice().showCinema().showName());
    assertEquals("blue", ticket.getSeance().getRoom().getName());
    assertEquals("Titanik", ticket.getSeance().getMovie().getName());
    assertEquals(2, ticket.getPlace().getRow().getNumber());
    assertEquals(1, ticket.getPlace().getNumber());
    assertEquals(59.99, ticket.getPrice(), 0);
    assertEquals(8, ticket.getCashOffice().getNumber());

    assertEquals(12, ticket.getSeance().getStartDateTime().get(Calendar.DAY_OF_MONTH));
    assertEquals(Calendar.APRIL, ticket.getSeance().getStartDateTime().get(Calendar.MONTH));
    assertEquals(2014, ticket.getSeance().getStartDateTime().get(Calendar.YEAR));
    assertEquals(10, ticket.getSeance().getStartDateTime().get(Calendar.HOUR_OF_DAY));
    assertEquals(30, ticket.getSeance().getStartDateTime().get(Calendar.MINUTE));
  }

  @Test
  public void shouldTranslateToElement() throws DataConversionException {
    Ticket ticket = new Ticket(new Place(5, new Row(7)));
    ticket.setPrice(35.0);

    Element element = new TicketTranslator().toElement(ticket);

    assertEquals("ticket", element.getName());
    assertEquals(7, element.getAttribute("row").getIntValue());
    assertEquals(5, element.getAttribute("place").getIntValue());
    assertEquals(35.0, element.getAttribute("price").getDoubleValue(), 0);

  }

  @Test(expected = TicketNotFullException.class)
  public void shouldRaiseExceptionWhenTicketNotFull() {
    new TicketTranslator().toElement(new Ticket());
  }
}
