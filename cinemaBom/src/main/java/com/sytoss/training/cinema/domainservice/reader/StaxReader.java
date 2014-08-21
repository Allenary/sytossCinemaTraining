package com.sytoss.training.cinema.domainservice.reader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
import com.sytoss.training.cinema.translator.CashOfficeTranslator;
import com.sytoss.training.cinema.translator.CinemaTranslator;
import com.sytoss.training.cinema.translator.MovieTranslator;
import com.sytoss.training.cinema.translator.PlaceTranslator;
import com.sytoss.training.cinema.translator.RoomTranslator;
import com.sytoss.training.cinema.translator.RowTranslator;
import com.sytoss.training.cinema.translator.SeanceTranslator;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class StaxReader extends AbstractXmlReader {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public List<Ticket> read(List<String> inputFileNames) {
    for (String fileName : inputFileNames) {
      if (isValidXML(fileName)) {
        try {
          parseXML(fileName);
        } catch (Exception e) {
          logger.error("Error during reading file " + fileName, e);
        }
      } else {
        logger.warn("file " + fileName + "do not correspond xsd");
      }
    }
    return tickets;
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
          if (tagName == "cinema") {
            cinema = new CinemaTranslator().fromDTO((xpp.getAttributeValue(null, "name")));
          }
          if (tagName == "cashOffice") {
            cashOffice = new CashOfficeTranslator().fromDTO((xpp.getAttributeValue(null, "id")));
          }
          if (tagName == "seance") {
            seanceStartDateTime = xpp.getAttributeValue(null, "startDateTime");
          }
          if (tagName == "ticket") {
            Row row = new RowTranslator().fromDTO(xpp.getAttributeValue(null, "row"));
            Place place = new PlaceTranslator().fromDTO(xpp.getAttributeValue(null, "place"));
            Ticket ticket = new TicketTranslator().fromDTO(xpp.getAttributeValue(null, "price"));

            row = room.registerRow(row);
            place.setRow(row);
            ticket.setPlace(place);

            seance.setMovie(movie);
            seance.setRoom(room);
            ticket.setSeance(seance);

            cinema.registerCashOffice(cashOffice);
            ticket.setCashOffice(cashOffice);

            cinema.registerSeance(seance);
            tickets.add(registerInCash(ticket));
          }
          break;
        case XmlPullParser.TEXT:
          text = xpp.getText();
          break;
        case XmlPullParser.END_TAG:
          if (tagName == "room") {
            room = new RoomTranslator().fromDTO(text);
            seance = new SeanceTranslator(SeanceTranslator.XML_DATE_FORMAT).fromDTO(seanceStartDateTime);
            seance.setMovie(movie);
          }
          if (tagName == "movie") {
            movie = new MovieTranslator().fromDTO(text);
          }
          break;

      }
      eventType = xpp.next();
    }
  }

}
