package com.sytoss.training.cinema.translator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import com.sytoss.training.cinema.bom.Place;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.exception.TicketNotFullException;

public class TicketTranslator {

  public String[] toDTO(Ticket ticket) throws ParseException {
    String[] dto = new String[8];
    try {
      String place = new PlaceTranslator().toDTO(ticket.getPlace());
      String row = new RowTranslator().toDTO(ticket.getPlace().getRow());

      String seance = new SeanceTranslator().toDTO(ticket.getSeance());
      String room = new RoomTranslator().toDTO(ticket.getSeance().getRoom());
      String movie = new MovieTranslator().toDTO(ticket.getSeance().getMovie());
      String cashOffice = new CashOfficeTranslator().toDTO(ticket.getCashOffice());
      String cinema = new CinemaTranslator().toDTO(ticket.getCashOffice().getCinema());

      DecimalFormatSymbols decimalSymbols = new DecimalFormatSymbols();
      decimalSymbols.setDecimalSeparator('.');
      String price = new DecimalFormat("0.00", decimalSymbols).format(ticket.getPrice());

      dto[0] = cinema;
      dto[1] = room;
      dto[2] = movie;
      dto[3] = seance;
      dto[4] = row;
      dto[5] = place;
      dto[6] = price;
      dto[7] = cashOffice;
    } catch (Exception e) {
      throw new ParseException("Ticket not full ", 0);
    }
    return dto;
  }

  public Ticket fromDTO(String ticketDTO) {
    Ticket ticket = new Ticket();
    ticket.setPrice(Double.parseDouble(ticketDTO));
    return ticket;
  }

  public Element toElement(Ticket ticket) {
    Element element = new Element("ticket");
    try {
      element.setAttribute("row", Integer.toString(ticket.getPlace().getRow().getNumber()));
      element.setAttribute("place", Integer.toString(ticket.getPlace().getNumber()));
      element.setAttribute("price", Double.toString(ticket.getPrice()));
    } catch (Exception e) {
      throw new TicketNotFullException("Not full data in ticket.");
    }
    return element;
  }

  public Ticket fromDTO(Element element) throws DataConversionException {
    Ticket ticket = new Ticket(
      new Place(element.getAttribute("place").getIntValue(), new Row(element.getAttribute("row").getIntValue())),
      element.getAttribute("price").getDoubleValue());
    return ticket;
  }
}
