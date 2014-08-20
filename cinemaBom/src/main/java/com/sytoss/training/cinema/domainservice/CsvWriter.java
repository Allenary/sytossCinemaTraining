package com.sytoss.training.cinema.domainservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.FileSystemConnector;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class CsvWriter implements IWriter {

  public void write(Map<String, Cinema> cinemas, String outputFileName) throws IOException {
    List<Ticket> tickets = getTicketsFromMap(cinemas);
    List<String> csvStrings = new ArrayList<String>();
    try {
      for (Ticket ticket : tickets) {
        csvStrings.add(new CsvParser(new SplitSplitStringStrategy()).deParse((new TicketTranslator().toDTO(ticket))));
      }
      new FileSystemConnector().write(csvStrings, outputFileName);
    } catch (Exception e) {
      throw new IOException(e);
    }

  }

  private List<Ticket> getTicketsFromMap(Map<String, Cinema> mapCinemas) {
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
