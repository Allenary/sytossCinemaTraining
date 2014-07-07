package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.sytoss.training.cinema.bom.Room;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.SeanceStatus;

import bom.exception.*;

public class SeanceTest {

  @Test
  public void shouldAddValidTicket() {
    Seance seance = new Seance();
    Ticket ticket = new Ticket();
    seance.addTicket(ticket);
    assertEquals(seance, ticket.getSeance());
    assertTrue(seance.contains(ticket));
  }

  @Test(expected = NullObjectInsertionException.class)
  public void shouldRaiseErrorForAddingNullTicket() {
    new Seance().addTicket(null);
  }

  @Test
  public void shouldSpecifyStartDateTime() {
    Seance seance = new Seance();
    Calendar calendar = new GregorianCalendar(2014, 6, 22);
    seance.setStartDateTime(calendar);
    assertEquals(calendar, seance.getStartDateTime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForPastStartDateTime() {
    Seance seance = new Seance();
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    seance.setStartDateTime(calendar);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullStartDateTime() {
    Seance seance = new Seance();
    seance.setStartDateTime(null);
  }

  // Status field test cover
  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullStatus() {
    Seance seance = new Seance();
    seance.setStatus(null);
  }

  @Test
  public void shouldSpecifyStatus() {
    Seance seance = new Seance();
    seance.setStatus(SeanceStatus.CANCEL);
    assertEquals(SeanceStatus.CANCEL, seance.getStatus());
  }

  // Seance[1] - [1]Room reference test cover
  @Test
  public void shouldSpecifyRoomInstance() {
    Seance seance = new Seance();
    Room room = new Room();
    room.setName("White");
    seance.setRoom(room);
    assertEquals(room, seance.getRoom());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseAnErrorForNullRoomInstance() {
    Seance seance = new Seance();
    seance.setRoom(null);
  }
}
