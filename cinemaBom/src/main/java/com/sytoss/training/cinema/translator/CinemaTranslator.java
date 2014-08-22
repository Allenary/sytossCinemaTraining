package com.sytoss.training.cinema.translator;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Room;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;

public class CinemaTranslator {

  public Cinema fromDTO(String cinemaDTO) {
    return new Cinema(cinemaDTO);
  }

  public String toDTO(Cinema cinema) {
    return cinema.getName();
  }

  public Element toElement(Cinema cinema) {
    Element element = new Element("cinema");
    element.setAttribute("name", cinema.getName());
    Iterator<CashOffice> cashOffices = cinema.cashOfficeIterator();
    while (cashOffices.hasNext()) {
      element.addContent(new CashOfficeTranslator().toElement(cashOffices.next()));
    }
    return element;
  }

  //  public Cinema fromDTO(Element element) throws DataConversionException, ParseException {
  //    Cinema cinema = new Cinema(element.getAttributeValue("name"));
  //    return cinema;
  //  }

  public Cinema fromDTO(Element element) throws DataConversionException, ParseException {
    Cinema cinema = new Cinema(element.getAttributeValue("name"));
    List<Element> cashOfficeElements = element.getChildren("cashOffice");
    for (Element cashOfficeElement : cashOfficeElements) {
      CashOffice cashOffice = new CashOfficeTranslator().fromDTO(cashOfficeElement);
      cinema.addCashOffice(cashOffice);
      List<Element> seanceElements = cashOfficeElement.getChildren("seance");
      for (Element seanceElement : seanceElements) {
        Seance seance = new SeanceTranslator().fromDTO(seanceElement);
        seance = cinema.registerSeance(seance);
        List<Element> ticketElements = seanceElement.getChild("tickets").getChildren();
        for (Element ticketElement : ticketElements) {
          Ticket ticket = new TicketTranslator().fromDTO(ticketElement);
          ticket.setCashOffice(cashOffice);
          Room room = seance.getRoom();
          ticket.getPlace().setRow((room.registerRow(ticket.getPlace().getRow())));
          ticket.setSeance(seance);
          //          seance.getRoom().registerRow(ticket.getPlace().getRow());
        }
      }
    }
    return cinema;
  }
}
