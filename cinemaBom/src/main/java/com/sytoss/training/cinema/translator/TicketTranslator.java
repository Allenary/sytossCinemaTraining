package com.sytoss.training.cinema.translator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Place;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.exception.TicketNotFullException;

public class TicketTranslator {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

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

  public Ticket fromDTO(String[] ticketDTO) throws ParseException {
    Ticket ticket = new Ticket();
    if (ticketDTO.length != 8) {
      String errorMessage = "could not convert DTO to Ticket. Reason: wrong count of params. Expected:8, Actual: " + ticketDTO.length;
      logger.error(errorMessage);
      throw new ParseException(errorMessage, 0);
    }

    Row row = new RowTranslator().fromDTO(ticketDTO[4]);
    Place place = new PlaceTranslator().fromDTO(ticketDTO[5]);
    place.setRow(row);
    ticket.setPlace(place);

    Seance seance = new SeanceTranslator().fromDTO(ticketDTO[3]);
    seance.setMovie(new MovieTranslator().fromDTO(ticketDTO[2]));
    seance.setRoom(new RoomTranslator().fromDTO(ticketDTO[1]));
    ticket.setSeance(seance);

    CashOffice cashOffice = new CashOfficeTranslator().fromDTO(ticketDTO[7]);
    cashOffice.setCinema(new CinemaTranslator().fromDTO(ticketDTO[0]));
    ticket.setCashOffice(cashOffice);

    ticket.setPrice(Double.parseDouble(ticketDTO[6]));

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
