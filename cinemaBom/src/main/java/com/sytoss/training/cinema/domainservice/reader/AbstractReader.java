package com.sytoss.training.cinema.domainservice.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Ticket;

public abstract class AbstractReader implements IReader {

  protected List<Ticket> tickets = new ArrayList<Ticket>();

  protected List<Cinema> cinemas = new ArrayList<Cinema>();

  protected Map<String, Cinema> mapCinemas;

  public AbstractReader() {
    mapCinemas = new HashMap<String, Cinema>();
  }

  public abstract List<Ticket> read(List<String> inputFileNames);

  protected Ticket registerInCash(Ticket ticket) {
    Cinema cinema = ticket.getCashOffice().getCinema();
    int index = cinemas.indexOf(cinema);
    if (index == -1) {
      cinemas.add(cinema);
    } else {
      Cinema foundedCinema = cinemas.get(index);
      ticket.setSeance(foundedCinema.registerSeance(ticket.getSeance()));
      ticket.setCashOffice(foundedCinema.registerCashOffice(ticket.getCashOffice()));
      Row row = ticket.getSeance().getRoom().registerRow(ticket.getPlace().getRow());
      ticket.setPlace(row.registerPlace(ticket.getPlace()));
      //      ticket.getPlace().getRow().getRoom().registerRow(ticket.getPlace().getRow());
    }
    return ticket;
  }

  protected List<Ticket> registerInCash(Cinema cinema) {
    List<Ticket> result = new ArrayList<Ticket>();
    Iterator<CashOffice> cashOfficeIterator = cinema.cashOfficeIterator();
    while (cashOfficeIterator.hasNext()) {
      Iterator<Ticket> ticketsIterator = cashOfficeIterator.next().tiketsIterator();
      while (ticketsIterator.hasNext()) {
        result.add(registerInCash(ticketsIterator.next()));
      }
    }
    return result;
  }

}
