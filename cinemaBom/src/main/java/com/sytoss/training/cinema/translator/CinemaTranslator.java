package com.sytoss.training.cinema.translator;

import java.util.Iterator;

import org.jdom2.Element;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;

public class CinemaTranslator {

  public Cinema fromDTO(String cinemaDTO) {
    return new Cinema(cinemaDTO);
  }

  public String toDTO(Cinema cinema) {
    return cinema.showName();
  }

  public Element toElement(Cinema cinema) {
    Element element = new Element("cinema");
    element.setAttribute("name", cinema.showName());
    Iterator<CashOffice> cashOffices = cinema.showCashOffices();
    while (cashOffices.hasNext()) {
      element.addContent(new CashOfficeTranslator().toElement(cashOffices.next()));
    }
    return element;
  }

}
