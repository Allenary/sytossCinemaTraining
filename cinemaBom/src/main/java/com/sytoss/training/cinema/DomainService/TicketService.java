package com.sytoss.training.cinema.DomainService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.sytoss.training.cinema.bom.Ticket;
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

}
