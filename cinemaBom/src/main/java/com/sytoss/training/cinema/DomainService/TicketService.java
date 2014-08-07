package com.sytoss.training.cinema.domainservice;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.FileSystemConnector;
import com.sytoss.training.cinema.translator.CinemaTranslator;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class TicketService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private List<Ticket> readFromFile(List<String> fileNames) {

    List<String> csvRows = new ArrayList<String>();
    List<Ticket> allTickets = new ArrayList<Ticket>();
    List<Ticket> ticketsIn1File;
    boolean isFileSkipped;
    for (String inputFile : fileNames) {
      isFileSkipped = false;
      logger.info("start with processing file: " + inputFile);
      ticketsIn1File = new ArrayList<Ticket>();
      try {
        csvRows = new FileSystemConnector().read(inputFile);
        logger.debug("Rows found: " + csvRows.size());
        for (String row : csvRows) {
          try {
            ticketsIn1File.add(new TicketTranslator().fromDTO(new CsvParser(new SplitSplitStringStrategy()).parse(row)));
            logger.debug("row '" + row + "' parsed");
          } catch (Exception e) {
            logger.warn("file " + inputFile + " was skipped. Reason: Corrupted data. Row: " + row);
            isFileSkipped = true;
          }
        }
      } catch (IOException e1) {
        isFileSkipped = true;
        logger.error(e1.getMessage());
      }
      if ( !isFileSkipped && equalsCashOfficeID(ticketsIn1File)) {
        allTickets.addAll(ticketsIn1File);
        logger.info("file added to merge: " + inputFile);
      }

    }
    logger.debug("count tickets=" + allTickets.size());
    return allTickets;

  }

  private void writeInFile(List<Ticket> tickets, String fileNameDestination) {
    List<String> csvStrings = new ArrayList<String>();
    try {
      for (Ticket ticket : tickets) {
        csvStrings.add(new CsvParser(new SplitSplitStringStrategy()).deParse((new TicketTranslator().toDTO(ticket))));
      }
      new FileSystemConnector().write(csvStrings, fileNameDestination);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  private boolean equalsCashOfficeID(List<Ticket> tickets) {
    if (tickets.size() == 0) {
      return false;
    }
    int firstRowCashOfficeID = tickets.get(0).getCashOffice().getNumber();
    for (Ticket ticket : tickets) {
      if (ticket.getCashOffice() == null || ticket.getCashOffice().getNumber() != firstRowCashOfficeID) {
        return false;
      }

    }
    return true;
  }

  public void mergeCSV(List<String> inputFileNames, String outputFileName) {
    writeInFile(readFromFile(inputFileNames), outputFileName);
  }

  private CashOffice getSameCashOfficeInCinema(Cinema cinema, CashOffice searchedCashOffice) {
    Iterator<CashOffice> cashOffices = cinema.cashOfficeIterator();
    CashOffice tempCashOffice;
    while (cashOffices.hasNext()) {
      tempCashOffice = cashOffices.next();
      if (tempCashOffice.equals(searchedCashOffice)) {
        return tempCashOffice;
      }
    }
    return null;
  }

  private List<Cinema> getCinemasFromTickets(List<Ticket> tickets) {
    List<Cinema> cinemas = new ArrayList<Cinema>();
    Cinema tempCinema;
    int tempIndexCinema;
    CashOffice tempCashOffice;
    for (Ticket ticket : tickets) {
      tempCinema = ticket.getCashOffice().getCinema();
      tempIndexCinema = cinemas.indexOf(tempCinema);
      if (tempIndexCinema == -1) {
        cinemas.add(tempCinema);
      } else {
        tempCashOffice = getSameCashOfficeInCinema(cinemas.get(tempIndexCinema), ticket.getCashOffice());
        if (tempCashOffice == null) {
          cinemas.get(tempIndexCinema).addCashOffice(ticket.getCashOffice());
        } else {
          tempCashOffice.addTicket(ticket);
        }
      }

    }
    return cinemas;
  }

  private void writeInXML(List<Cinema> cinemas, String fileNameDestination) throws IOException {
    List<Element> cinemaElements = new ArrayList<Element>();
    for (Cinema cinema : cinemas) {
      cinemaElements.add(new CinemaTranslator().toElement(cinema));
    }
    Element rootElement = new Element("cinemas");
    rootElement.addContent(cinemaElements);
    Document document = new Document();
    document.setRootElement(rootElement);
    new FileSystemConnector().write(document, fileNameDestination);
  }

  public void mergeCSVToXML(List<String> inputFileNames, String fileNameDestination) throws IOException {
    List<Ticket> tickets = readFromFile(inputFileNames);
    List<Cinema> cinemas = getCinemasFromTickets(tickets);
    writeInXML(cinemas, fileNameDestination);
  }

  public void mergeXML(List<String> inputFiles, String absolutePath) throws JDOMException, IOException, ParseException {
    List<Cinema> cinemas = new ArrayList<Cinema>();
    FileSystemConnector fsc = new FileSystemConnector();
    for (String inputFile : inputFiles) {
      Document doc = fsc.readXMLFileJDOM(inputFile);
      List<Element> cinemaElements = doc.getRootElement().getChildren("cinema");
      for (Element cinemaElement : cinemaElements) {
        cinemas.add(new CinemaTranslator().fromDTO(cinemaElement));
      }
    }
    writeInXML(cinemas, absolutePath);
  }
}
