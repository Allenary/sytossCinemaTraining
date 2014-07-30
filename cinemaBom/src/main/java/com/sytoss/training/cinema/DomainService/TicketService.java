package com.sytoss.training.cinema.DomainService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.CSVFileSystemConnector;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class TicketService {

  private List<Ticket> read(List<String> csvStrings) throws ParseException {
    List<Ticket> tickets = new ArrayList<Ticket>();
    String[] ticketParameters;
    for (String row : csvStrings) {
      ticketParameters = new CsvParser().parse(row);
      tickets.add(new TicketTranslator().fromDTO(ticketParameters));
    }
    return tickets;

  }

  public void write(List<Ticket> tickets, String fileNameDestination) {
    List<String> csvStrings = new ArrayList<String>();
    for (Ticket ticket : tickets) {
      csvStrings.add(new CsvParser().deParse((new TicketTranslator().toDTO(ticket))));
    }
    new CSVFileSystemConnector().write(csvStrings, fileNameDestination);
  }

  public boolean equalsCashOfficeID(List<Ticket> tickets) {
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
}
