package com.sytoss.training.cinema.translator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sytoss.training.cinema.bom.CashOffice;

public class CashOfficeTranslatorTest {

  @Test
  public void shouldTranslateToDTO() {
    String cashOfficeDTO = new CashOfficeTranslator().toDTO(new CashOffice(8));
    assertEquals("8", cashOfficeDTO);
  }

  @Test
  public void shouldTranslateFromDTO() {
    CashOffice cashOffice = new CashOfficeTranslator().fromDTO("1");
    assertEquals(1, cashOffice.getNumber());
  }
}
