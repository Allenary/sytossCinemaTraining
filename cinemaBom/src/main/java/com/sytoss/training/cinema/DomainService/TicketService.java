package com.sytoss.training.cinema.domainservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
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
      logger.warn("start with processing file: " + inputFile);
      ticketsIn1File = new ArrayList<Ticket>();
      try {
        csvRows = new FileSystemConnector().read(inputFile);
        logger.warn("Rows found: " + csvRows.size());
        for (String row : csvRows) {
          try {
            ticketsIn1File.add(new TicketTranslator().fromDTO(new CsvParser(new SplitSplitStringStrategy()).parse(row)));
            logger.warn("row '" + row + "' parsed");
          } catch (Exception e) {
            logger.warn("file " + inputFile + " was skipped. Reason: Corrupted data. Row: " + row);
            isFileSkipped = true;
          }
        }
      } catch (IOException e1) {
        isFileSkipped = true;
        logger.error(e1.getMessage());
        e1.printStackTrace();
      }
      if ( !isFileSkipped && equalsCashOfficeID(ticketsIn1File)) {
        allTickets.addAll(ticketsIn1File);
        logger.warn("file added to merge: " + inputFile);
      }

    }
    logger.warn("count tickets=" + allTickets.size());
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
      e.printStackTrace();
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
    Iterator<CashOffice> cashOffices = cinema.showCashOffices();
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
      tempCinema = ticket.getCashOffice().showCinema();
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

  public void mergeCSVToXML(List<String> inputFileNames, String fileNameDestination) throws IOException {
    List<Ticket> tickets = readFromFile(inputFileNames);
    List<Cinema> cinemas = getCinemasFromTickets(tickets);
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
}
