package com.sytoss.training.cinema.domainservice.writer;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.FileSystemConnector;
import com.sytoss.training.cinema.translator.MovieTranslator;
import com.sytoss.training.cinema.translator.PlaceTranslator;
import com.sytoss.training.cinema.translator.RoomTranslator;
import com.sytoss.training.cinema.translator.RowTranslator;
import com.sytoss.training.cinema.translator.SeanceTranslator;

public class StaxXmlWriter extends AbstractXmlWriter {

  public void write(List<Ticket> tickets, String outputFileName) throws IOException {
    List<Cinema> cinemas = getCinemasFromTickets(tickets);
    try {

      XmlPullParserFactory xpp = XmlPullParserFactory.newInstance();
      XmlSerializer serializer = xpp.newSerializer();
      FileSystemConnector fileSystemConnector = new FileSystemConnector();
      fileSystemConnector.writeSTAX(serializer, outputFileName);
      serializer.startDocument("UTF-8", null);
      serializer.text("\r\n");
      serializer.startTag(null, "cinemas");
      for (Cinema cinema : cinemas) {

        serializer.text("\r\n  ");
        serializer.startTag(null, "cinema");
        serializer.attribute(null, "name", cinema.getName());
        for (Iterator<CashOffice> iterator = cinema.cashOfficeIterator(); iterator.hasNext();) {

          serializer.text("\r\n    ");
          CashOffice cashOffice = iterator.next();
          serializer.startTag(null, "cashOffice");
          serializer.attribute(null, "id", Integer.toString(cashOffice.getNumber()));

          for (Seance seance : cashOffice.getSeances()) {
            serializer.text("\r\n      ");
            serializer.startTag(null, "seance");
            serializer.attribute(null, "startDateTime", new SeanceTranslator(SeanceTranslator.XML_DATE_FORMAT).toDTO(seance));
            serializer.text("\r\n        ");

            serializer.startTag(null, "movie");
            serializer.text(new MovieTranslator().toDTO(seance.getMovie()));
            serializer.endTag(null, "movie");
            serializer.text("\r\n        ");

            serializer.startTag(null, "room");
            serializer.text(new RoomTranslator().toDTO(seance.getRoom()));
            serializer.endTag(null, "room");
            serializer.text("\r\n        ");

            serializer.startTag(null, "tickets");
            for (Ticket ticket : seance.getTicketsByCOid(cashOffice.getNumber())) {
              serializer.text("\r\n          ");
              serializer.startTag(null, "ticket");
              serializer.attribute(null, "row", new RowTranslator().toDTO(ticket.getPlace().getRow()));
              serializer.attribute(null, "place", new PlaceTranslator().toDTO(ticket.getPlace()));
              serializer.attribute(null, "price", Double.toString(ticket.getPrice()));
              serializer.endTag(null, "ticket");
            }
            serializer.text("\r\n        ");
            serializer.endTag(null, "tickets");
            serializer.text("\r\n      ");
            serializer.endTag(null, "seance");
          }
          serializer.text("\r\n    ");
          serializer.endTag(null, "cashOffice");
        }
        serializer.text("\r\n  ");
        serializer.endTag(null, "cinema");
      }
      serializer.text("\r\n");
      serializer.endTag(null, "cinemas");
      serializer.text("\r\n");
      serializer.endDocument();
    } catch (Exception e) {
      throw new IOException("Error occured during writing to file " + outputFileName, e);
    }
  }
}
