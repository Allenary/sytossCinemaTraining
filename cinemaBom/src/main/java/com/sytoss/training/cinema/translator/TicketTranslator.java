package com.sytoss.training.cinema.translator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Movie;
import com.sytoss.training.cinema.bom.Place;
import com.sytoss.training.cinema.bom.Room;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;

public class TicketTranslator {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private String dateFormat = "dd.MM.yyyy HH:mm";

  public String[] toDTO(Ticket ticket) {
    String[] dto = new String[8];
    String place = "";
    String row = "";
    String room = "";
    String cinema = "";
    String movie = "";
    String seance = "";
    String price = "";
    String cashOffice = "";

    if (ticket.getPlace() != null) {
      place = new PlaceTranslator().toDTO(ticket.getPlace());
      if (ticket.getPlace().getRow() != null) {
        row = new RowTranslator().toDTO(ticket.getPlace().getRow());
      }
    }

    if (ticket.getSeance() != null) {
      seance = new SeanceTranslator().toDTO(ticket.getSeance());
      if (ticket.getSeance().getRoom() != null) {
        room = new RoomTranslator().toDTO(ticket.getSeance().getRoom());
      }
      if (ticket.getSeance().getMovie() != null) {
        movie = new MovieTranslator().toDTO(ticket.getSeance().getMovie());
      }
    }

    if (ticket.getCashOffice() != null) {
      cashOffice = new CashOfficeTranslator().toDTO(ticket.getCashOffice());
      if (ticket.getCashOffice().showCinema() != null) {
        cinema = new CinemaTranslator().toDTO(ticket.getCashOffice().showCinema());
      }
    }

    DecimalFormatSymbols decimalSymbols = new DecimalFormatSymbols();
    decimalSymbols.setDecimalSeparator('.');
    price = new DecimalFormat("0.00", decimalSymbols).format(ticket.getPrice());

    dto[0] = cinema;
    dto[1] = room;
    dto[2] = movie;
    dto[3] = seance;
    dto[4] = row;
    dto[5] = place;
    dto[6] = price;
    dto[7] = cashOffice;
    return dto;
  }

  public Ticket fromDTO(String[] ticketDTO) throws ParseException {
    Ticket ticket = new Ticket();
    if (ticketDTO.length != 8) {
      String errorMessage = "could not convert DTO to Ticket. Reason: wrong count of params. Expected:8, Actual: " + ticketDTO.length;
      logger.error(errorMessage);
      throw new ParseException(errorMessage, 0);
    }

    try {
      Row row = new Row(Integer.parseInt(ticketDTO[4]));
      Place place = new Place(Integer.parseInt(ticketDTO[5]), row);
      ticket.setPlace(place);

      Calendar calendar = Calendar.getInstance();

      calendar.setTime(new SimpleDateFormat(dateFormat).parse(ticketDTO[3]));
      Seance seance = new Seance(new Room(ticketDTO[1]), calendar);
      seance.setMovie(new Movie(ticketDTO[2]));
      ticket.setSeance(seance);

      CashOffice cashOffice = new CashOffice(Integer.parseInt(ticketDTO[7]));
      cashOffice.setCinema(new Cinema(ticketDTO[0]));
      ticket.setCashOffice(cashOffice);

      ticket.setPrice(Double.parseDouble(ticketDTO[6]));

    } catch (Exception e) {
      logger.error("could not convert DTO to Ticket. Reason: parse exception.");
      throw new ParseException(e.getMessage(), 0);
    }
    return ticket;
  }
}
