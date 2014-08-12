package com.sytoss.training.cinema.translator;

import java.text.ParseException;
import java.util.Iterator;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;

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

  public Cinema fromDTO(Element element) throws DataConversionException, ParseException {
    Cinema cinema = new Cinema(element.getAttributeValue("name"));
    return cinema;
  }
}
