package com.sytoss.training.cinema.translator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sytoss.training.cinema.bom.Seance;

public class SeanceTranslator {

  public String toDTO(Seance seance) {
    return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(seance.getStartDateTime().getTime());
  }

  public Seance fromDTO(String seanceDTO) throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(seanceDTO));
    Seance seance = new Seance();
    seance.setStartDateTime(calendar);
    return seance;
  }

}
