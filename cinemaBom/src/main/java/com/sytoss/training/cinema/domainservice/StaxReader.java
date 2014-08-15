package com.sytoss.training.cinema.domainservice;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Movie;
import com.sytoss.training.cinema.bom.Place;
import com.sytoss.training.cinema.bom.Room;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.FileSystemConnector;
import com.sytoss.training.cinema.translator.SeanceTranslator;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class StaxReader extends AbstractReader {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public Map<String, Cinema> read(List<String> inputFileNames) {
    for (String fileName : inputFileNames) {
      try {
        parseXML(fileName);
      } catch (Exception e) {
        logger.error("Error during reading file " + fileName, e);
      }
    }
    return mapCinemas;
  }

  private void parseXML(String inputFileName) throws XmlPullParserException, IOException, ParseException {
    XmlPullParser xpp = new FileSystemConnector().readXMLFileSTAX(inputFileName);
    int eventType = xpp.getEventType();

    Cinema cinema = null;
    CashOffice cashOffice = null;
    Seance seance = null;
    String seanceStartDateTime = null;
    Room room = null;
    String text = null;
    Movie movie = null;
    while (eventType != XmlPullParser.END_DOCUMENT) {
      String tagName = xpp.getName();
      switch (eventType) {
        case XmlPullParser.START_TAG:
          switch (tagName) {
            case "cinema":
              cinema = findOrCreateNewCinema(xpp.getAttributeValue(null, "name"));
              break;
            case "cashOffice":
              cashOffice = findOrCreateNewCO(xpp.getAttributeValue(null, "id"), cinema);
              break;
            case "seance":
              seanceStartDateTime = xpp.getAttributeValue(null, "startDateTime");
              break;
            case "ticket":
              Row row = findOrCreateNewRow(xpp.getAttributeValue(null, "row"), room);
              Place place = findOrCreateNewPlace(xpp.getAttributeValue(null, "place"), row);
              Ticket ticket = new TicketTranslator().fromDTO(xpp.getAttributeValue(null, "price"));
              ticket.setPlace(place);
              ticket.setCashOffice(cashOffice);
              ticket.setSeance(seance);
              break;
          }
          break;
        case XmlPullParser.TEXT:
          text = xpp.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagName == "room") {
            room = findOrCreateNewRoom(text, cinema);
            seance = findOrCreateNewSeance(seanceStartDateTime, cinema, room, SeanceTranslator.XML_DATE_FORMAT);
            seance.setMovie(movie);
          }
          if (tagName == "movie") {
            //            movie = findOrCreateNewMovie(text, cinema);
            movie = cinema.findOrCreateNewMovie(text);
          }
          break;

      }
      eventType = xpp.next();
    }
  }

}
