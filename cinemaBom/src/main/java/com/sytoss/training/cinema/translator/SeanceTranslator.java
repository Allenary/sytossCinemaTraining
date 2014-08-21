package com.sytoss.training.cinema.translator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.exception.SeanceNotFullException;

public class SeanceTranslator {

  public static final String CSV_DATE_FORMAT = "dd.MM.yyyy HH:mm";

  public static final String XML_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  private String currentDateFormat;

  public SeanceTranslator(String dateFormat) {
    currentDateFormat = dateFormat;
  }

  public SeanceTranslator() {
    this(CSV_DATE_FORMAT);
  }

  public String toDTO(Seance seance) {
    return new SimpleDateFormat(currentDateFormat).format(seance.getStartDateTime().getTime());
  }

  public Seance fromDTO(String seanceDTO) throws ParseException {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new SimpleDateFormat(currentDateFormat).parse(seanceDTO));
    Seance seance = new Seance();
    seance.setStartDateTime(calendar);
    return seance;
  }

  public Element toElement(Seance seance) {
    Element seanceElement = new Element("seance");
    try {
      seanceElement.setAttribute("startDateTime", new SimpleDateFormat(XML_DATE_FORMAT).format(seance.getStartDateTime().getTime()));
      seanceElement.addContent(new MovieTranslator().toElement(seance.getMovie()));
      seanceElement.addContent(new RoomTranslator().toElement(seance.getRoom()));
    } catch (Exception e) {
      throw new SeanceNotFullException("no room or movie or date", e);
    }
    return seanceElement;
  }

  public Seance fromDTO(Element element) throws ParseException, DataConversionException {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new SimpleDateFormat(XML_DATE_FORMAT).parse(element.getAttributeValue("startDateTime")));
    Seance seance = new Seance();
    seance.setStartDateTime(calendar);
    seance.setRoom(new RoomTranslator().fromDTO(element.getChild("room")));
    seance.setMovie(new MovieTranslator().fromDTO(element.getChild("movie")));
    return seance;
  }
}
