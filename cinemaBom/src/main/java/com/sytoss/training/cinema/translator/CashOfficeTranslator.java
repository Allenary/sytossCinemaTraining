package com.sytoss.training.cinema.translator;

import java.util.List;

import org.jdom2.Element;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Seance;

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

}
