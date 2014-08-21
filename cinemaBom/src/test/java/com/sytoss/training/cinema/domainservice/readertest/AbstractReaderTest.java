package com.sytoss.training.cinema.domainservice.readertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Movie;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.domainservice.reader.IReader;

public abstract class AbstractReaderTest {

  protected String folder = "/shouldCreateSingleBOMObjectForTicketsWithSameData";

  public abstract IReader getReader();

  public abstract List<String> getInputFiles() throws URISyntaxException;

  @Test
  public void shouldCreateSingleBOMObjectForTicketsWithSameData() throws URISyntaxException {

    List<Ticket> tickets = getReader().read(getInputFiles());

    assertEquals(3, tickets.size());

    Cinema cinema = tickets.get(0).getCashOffice().getCinema();
    assertEquals(cinema, tickets.get(2).getCashOffice().getCinema());
    assertEquals(cinema, tickets.get(1).getCashOffice().getCinema());

    CashOffice cashOffice = tickets.get(0).getCashOffice();

    assertEquals(cashOffice, tickets.get(1).getCashOffice());
    assertEquals(cashOffice, tickets.get(2).getCashOffice());
    assertTrue(cinema.existCashOffice(cashOffice));

    assertTrue(cinema.existSeance(tickets.get(0).getSeance()));
    assertEquals(tickets.get(0).getSeance(), tickets.get(1).getSeance());
    assertTrue(cinema.existSeance(tickets.get(2).getSeance()));

    assertEquals(tickets.get(0).getSeance().getRoom(), tickets.get(2).getSeance().getRoom());
    assertTrue(cinema.existRoom(tickets.get(0).getSeance().getRoom()));

    assertEquals(tickets.get(0).getPlace().getRow(), tickets.get(2).getPlace().getRow());
    assertTrue(tickets.get(0).getSeance().getRoom().exists(tickets.get(0).getPlace().getRow()));
    assertTrue(tickets.get(0).getSeance().getRoom().exists(tickets.get(1).getPlace().getRow()));

    assertTrue(cinema.existMovie(tickets.get(0).getSeance().getMovie()));
    Iterator<Movie> movies = cinema.movieIterator();
    assertTrue(movies.hasNext());
    movies.next();
    assertFalse(movies.hasNext());
    assertEquals(tickets.get(0).getSeance().getMovie(), tickets.get(2).getSeance().getMovie());
  }
}
