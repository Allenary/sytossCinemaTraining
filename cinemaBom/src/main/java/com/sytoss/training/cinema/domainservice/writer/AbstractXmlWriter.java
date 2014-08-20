package com.sytoss.training.cinema.domainservice.writer;

import java.util.ArrayList;
import java.util.List;

import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Ticket;

public abstract class AbstractXmlWriter implements IWriter {

  protected List<Cinema> getCinemasFromTickets(List<Ticket> tickets) {
    List<Cinema> cinemas = new ArrayList<Cinema>();
    for (Ticket ticket : tickets) {
      if ( !cinemas.contains(ticket.getCashOffice().getCinema())) {
        cinemas.add(ticket.getCashOffice().getCinema());
      }
    }
    return cinemas;
  }

}
