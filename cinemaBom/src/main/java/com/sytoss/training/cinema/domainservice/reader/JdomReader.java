package com.sytoss.training.cinema.domainservice.reader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.FileSystemConnector;
import com.sytoss.training.cinema.translator.CinemaTranslator;

public class JdomReader extends AbstractXmlReader {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public List<Ticket> read(List<String> inputFileNames) {
    for (String inputFile : inputFileNames) {
      if (isValidXML(inputFile)) {
        try {
          readFromXMLFileJDOM(inputFile);
        } catch (Exception e) {
          logger.error("Error occured during reading file: " + inputFile + e.getStackTrace().toString(), e);
        }
      } else {
        logger.warn("file " + inputFile + "do not correspond xsd");
      }
    }
    return tickets;
  }

  private void readFromXMLFileJDOM(String inputFile) throws JDOMException, IOException, ParseException {
    FileSystemConnector fsc = new FileSystemConnector();
    Document doc = fsc.readXMLFileJDOM(inputFile);
    List<Element> cinemaElements = doc.getRootElement().getChildren("cinema");
    for (Element cinemaElement : cinemaElements) {
      Cinema cinema = new CinemaTranslator().fromDTO(cinemaElement);
      tickets.addAll(registerInCash(cinema));
    }
  }
}
