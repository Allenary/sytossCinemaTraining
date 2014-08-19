package com.sytoss.training.cinema.domainservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.FileSystemConnector;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class TicketService {

  private IWriter xmlWriter;

  private IReader xmlReader;

  private IReader csvReader;

  private Map<String, Cinema> mapCinemas;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public TicketService() {
    this(new StaxReader(), new JdomXmlWriter());
  }

  public TicketService(IReader reader, IWriter writer) {
    xmlReader = reader;
    xmlWriter = writer;
    mapCinemas = new HashMap<String, Cinema>();
    csvReader = new CsvReader();
  }

  private void writeInCSV(List<Ticket> tickets, String fileNameDestination) {
    List<String> csvStrings = new ArrayList<String>();
    try {
      for (Ticket ticket : tickets) {
        csvStrings.add(new CsvParser(new SplitSplitStringStrategy()).deParse((new TicketTranslator().toDTO(ticket))));
      }
      new FileSystemConnector().write(csvStrings, fileNameDestination);
    } catch (Exception e) {
      logger.error("error during writing to file " + fileNameDestination, e);
    }
  }

  public void ReadCSV(List<String> inputFileNames) {
    mapCinemas.putAll(csvReader.read(inputFileNames));
  }

  public void mergeCSV(List<String> inputFileNames, String outputFileName) {
    ReadCSV(inputFileNames);
    writeInCSV(getTicketsFromMap(), outputFileName);
  }

  public void mergeCSVToXML(List<String> inputFileNames, String fileNameDestination) {
    try {
      mapCinemas.putAll(csvReader.read(inputFileNames));
      xmlWriter.write(new ArrayList<Cinema>(mapCinemas.values()), fileNameDestination);
    } catch (IOException e) {
      logger.error("Error occured during writing to file " + fileNameDestination, e);
    }
  }

  public void mergeXML(List<String> inputFiles, String fileNameDestination) {
    mapCinemas.putAll(xmlReader.read(inputFiles));
    try {
      xmlWriter.write(new ArrayList<Cinema>(mapCinemas.values()), fileNameDestination);
    } catch (IOException e) {
      logger.error("Error occured during writing to file " + fileNameDestination, e);
    }
  }

  public List<Ticket> getTicketsFromMap() {
    List<Ticket> tickets = new ArrayList<Ticket>();
    for (Cinema cinema : mapCinemas.values()) {
      for (Iterator<CashOffice> coIterator = cinema.cashOfficeIterator(); coIterator.hasNext();) {
        CashOffice cashOffice = coIterator.next();
        for (Iterator<Ticket> ticketIterator = cashOffice.tiketsIterator(); ticketIterator.hasNext();) {
          tickets.add(ticketIterator.next());
        }
      }
    }
    return tickets;
  }

}
