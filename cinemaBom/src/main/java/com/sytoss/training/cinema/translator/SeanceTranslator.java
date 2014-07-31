package com.sytoss.training.cinema.translator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sytoss.training.cinema.bom.Seance;

public class SeanceTranslator {

  private String dateFormat = "dd.MM.yyyy HH:mm";

  public String toDTO(Seance seance) {
    return new SimpleDateFormat(dateFormat).format(seance.getStartDateTime().getTime());
  }

  public Seance fromDTO(String seanceDTO) throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new SimpleDateFormat(dateFormat).parse(seanceDTO));
    Seance seance = new Seance();
    seance.setStartDateTime(calendar);
    return seance;
  }

}
