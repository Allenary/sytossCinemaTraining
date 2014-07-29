package com.sytoss.training.cinema.translator;

import com.sytoss.training.cinema.bom.CashOffice;

public class CashOfficeTranslator {

  public CashOffice fromDTO(String cashOfficeDTO) {
    return new CashOffice(Integer.parseInt(cashOfficeDTO));
  }

  public String toDTO(CashOffice cashOffice) {
    return Integer.toString(cashOffice.getNumber());
  }

}
