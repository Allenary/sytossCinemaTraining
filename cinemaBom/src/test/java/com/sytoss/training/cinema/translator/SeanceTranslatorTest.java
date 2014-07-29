package com.sytoss.training.cinema.translator;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.Test;

import com.sytoss.training.cinema.bom.Seance;

public class SeanceTranslatorTest {

  @Test
  public void shouldTranslateToDTO() {
    Seance seance = new Seance();
    Calendar date = Calendar.getInstance();
    date.set(2014, Calendar.SEPTEMBER, 20, 10, 45);
    seance.setStartDateTime(date);

    String seanceDTO = new SeanceTranslator().toDTO(seance);

    assertEquals("20.09.2014 10:45", seanceDTO);
  }

  @Test
  public void shouldTranslateFromDTO() throws ParseException {
    Calendar date = Calendar.getInstance();
    date.set(2014, Calendar.SEPTEMBER, 20, 10, 45);

    Seance seance = new SeanceTranslator().fromDTO("20.09.2014 10:45");
    assertEquals(date.get(Calendar.YEAR), seance.getStartDateTime().get(Calendar.YEAR));
    assertEquals(date.get(Calendar.MONTH), seance.getStartDateTime().get(Calendar.MONTH));
    assertEquals(date.get(Calendar.DAY_OF_MONTH), seance.getStartDateTime().get(Calendar.DAY_OF_MONTH));
    assertEquals(date.get(Calendar.HOUR_OF_DAY), seance.getStartDateTime().get(Calendar.HOUR_OF_DAY));
    assertEquals(date.get(Calendar.MINUTE), seance.getStartDateTime().get(Calendar.MINUTE));
  }
}
