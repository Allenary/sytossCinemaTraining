package com.sytoss.training.cinema.translator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.jdom2.Element;

import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;

public class SeanceTranslator {

  private String csvDateFormat = "dd.MM.yyyy HH:mm";

  private String xmlDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  public String toDTO(Seance seance) {
    return new SimpleDateFormat(csvDateFormat).format(seance.getStartDateTime().getTime());
  }

  public Seance fromDTO(String seanceDTO) throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new SimpleDateFormat(csvDateFormat).parse(seanceDTO));
    Seance seance = new Seance();
    seance.setStartDateTime(calendar);
    return seance;
  }

  public Element toElement(Seance seance) {
    Element seanceElement = new Element("seance");
    seanceElement.setAttribute("startDateTime", new SimpleDateFormat(xmlDateFormat).format(seance.getStartDateTime().getTime()));
    seanceElement.addContent(new MovieTranslator().toElement(seance.getMovie()));
    seanceElement.addContent(new RoomTranslator().toElement(seance.getRoom()));
    Element ticketsElement = new Element("tickets");
    Iterator<Ticket> seanceTickets = seance.getTickets();
    while (seanceTickets.hasNext()) {
      ticketsElement.addContent(new TicketTranslator().toElement(seanceTickets.next()));
    }
    seanceElement.addContent(ticketsElement);
    return seanceElement;
  }
}
