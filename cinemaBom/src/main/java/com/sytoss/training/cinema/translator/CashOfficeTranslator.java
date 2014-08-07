package com.sytoss.training.cinema.translator;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;

public class CashOfficeTranslator {

  public CashOffice fromDTO(String cashOfficeDTO) {
    return new CashOffice(Integer.parseInt(cashOfficeDTO));
  }

  public String toDTO(CashOffice cashOffice) {
    return Integer.toString(cashOffice.getNumber());
  }

  public Element toElement(CashOffice cashOffice) {
    Element cashOfficeElement = new Element("cashOffice");
    List<Seance> seances = cashOffice.getSeances();
    for (Seance seance : seances) {
      cashOfficeElement.addContent(new SeanceTranslator().toElement(seance));
    }
    cashOfficeElement.setAttribute("id", Integer.toString(cashOffice.getNumber()));
    return cashOfficeElement;
  }

  public CashOffice fromDTO(Element cashOfficeElement) throws DataConversionException, ParseException {
    CashOffice cashOffice = new CashOffice(cashOfficeElement.getAttribute("id").getIntValue());
    List<Element> seanceElements = cashOfficeElement.getChildren("seance");
    for (Element seanceElement : seanceElements) {
      Seance seance = new SeanceTranslator().fromDTO(seanceElement);
      Iterator<Ticket> ticketsIterator = seance.getTickets();
      while (ticketsIterator.hasNext()) {
        Ticket ticket = ticketsIterator.next();
        cashOffice.addTicket(ticket);
      }
    }
    return cashOffice;
  }

}
