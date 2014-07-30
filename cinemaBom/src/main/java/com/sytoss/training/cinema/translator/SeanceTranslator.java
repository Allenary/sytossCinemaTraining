package com.sytoss.training.cinema.translator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sytoss.training.cinema.bom.Seance;

public class SeanceTranslator {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private String dateFormat = "dd.MM.yyyy HH:mm";

  public String toDTO(Seance seance) {
    return new SimpleDateFormat(dateFormat).format(seance.getStartDateTime().getTime());
  }

  public Seance fromDTO(String seanceDTO) {
    Calendar calendar = Calendar.getInstance();
    try {
      calendar.setTime(new SimpleDateFormat(dateFormat).parse(seanceDTO));
    } catch (ParseException e) {
      logger.error("date '" + seanceDTO + "' do not correspond format '" + dateFormat);
      calendar = null;
    }
    Seance seance = new Seance();
    seance.setStartDateTime(calendar);
    return seance;
  }

}
