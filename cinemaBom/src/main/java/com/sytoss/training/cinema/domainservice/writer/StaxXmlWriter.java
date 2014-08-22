package com.sytoss.training.cinema.domainservice.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.translator.MovieTranslator;
import com.sytoss.training.cinema.translator.PlaceTranslator;
import com.sytoss.training.cinema.translator.RoomTranslator;
import com.sytoss.training.cinema.translator.RowTranslator;
import com.sytoss.training.cinema.translator.SeanceTranslator;

@SuppressWarnings("restriction")
public class StaxXmlWriter extends AbstractXmlWriter {

  public void write(List<Ticket> tickets, String outputFileName) throws IOException {
    List<Cinema> cinemas = getCinemasFromTickets(tickets);
    try {
      OutputStream outputStream = new FileOutputStream(new File(outputFileName));
      XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(new OutputStreamWriter(outputStream, "utf-8"));
      out.writeStartDocument("UTF-8", "1.0");
      out.writeCharacters("\r\n");
      out.writeStartElement("cinemas");

      for (Cinema cinema : cinemas) {
        out.writeCharacters("\r\n  ");
        out.writeStartElement("cinema");
        out.writeAttribute("name", cinema.getName());

        for (Iterator<CashOffice> iterator = cinema.cashOfficeIterator(); iterator.hasNext();) {
          out.writeCharacters("\r\n    ");

          CashOffice cashOffice = iterator.next();
          out.writeStartElement("cashOffice");
          out.writeAttribute("id", Integer.toString(cashOffice.getNumber()));

          for (Seance seance : cashOffice.getSeances()) {
            out.writeCharacters("\r\n      ");
            out.writeStartElement("seance");
            out.writeAttribute("startDateTime", new SeanceTranslator(SeanceTranslator.XML_DATE_FORMAT).toDTO(seance));
            out.writeCharacters("\r\n        ");

            out.writeStartElement("movie");
            out.writeCharacters(new MovieTranslator().toDTO(seance.getMovie()));
            out.writeEndElement();
            out.writeCharacters("\r\n        ");

            out.writeStartElement("room");
            out.writeCharacters(new RoomTranslator().toDTO(seance.getRoom()));
            out.writeEndElement();
            out.writeCharacters("\r\n        ");

            out.writeStartElement("tickets");
            for (Ticket ticket : seance.getTicketsByCOid(cashOffice.getNumber())) {
              out.writeCharacters("\r\n          ");
              out.writeEmptyElement("ticket");
              out.writeAttribute("row", new RowTranslator().toDTO(ticket.getPlace().getRow()));
              out.writeAttribute("place", new PlaceTranslator().toDTO(ticket.getPlace()));
              out.writeAttribute("price", Double.toString(ticket.getPrice()));
            }
            out.writeCharacters("\r\n        ");
            out.writeEndElement(); //end tickets
            out.writeCharacters("\r\n      ");
            out.writeEndElement(); // end seance
          }
          out.writeCharacters("\r\n    ");
          out.writeEndElement();// end CO
        }
        out.writeCharacters("\r\n  ");
        out.writeEndElement();// end cinema
      }
      out.writeCharacters("\r\n");
      out.writeEndElement(); // end cinemas
      out.writeCharacters("\r\n");
      out.writeEndDocument();
      out.close();
    } catch (Exception e) {
      throw new IOException("Error occured during writing to file " + outputFileName, e);
    }
  }
}
