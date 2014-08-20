package com.sytoss.training.cinema.domainservice.writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.FileSystemConnector;
import com.sytoss.training.cinema.translator.CinemaTranslator;

public class JdomXmlWriter extends AbstractXmlWriter {

  public void write(List<Ticket> tickets, String outputFileName) throws IOException {
    List<Element> cinemaElements = new ArrayList<Element>();
    for (Cinema cinema : getCinemasFromTickets(tickets)) {
      cinemaElements.add(new CinemaTranslator().toElement(cinema));
    }
    Element rootElement = new Element("cinemas");
    rootElement.addContent(cinemaElements);
    Document document = new Document();
    document.setRootElement(rootElement);
    new FileSystemConnector().writeJDOM(document, outputFileName);
  }

}
