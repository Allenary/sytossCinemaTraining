package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class SeanceTest {

  // StartDateTime field test cover
	@Test
	public void testSetStartDateTimeShouldBeCorrect() {
	  Seance seance = new Seance();
		Calendar calendar = new GregorianCalendar(2014, 6, 22);
		seance.setStartDateTime(calendar);
		assertEquals(calendar, seance.getStartDateTime());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStartDateTimeShouldBeBiggerThanCurrent() {
	  Seance seance = new Seance();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		seance.setStartDateTime(calendar);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStartDateTimeShouldBeNotNull() {
	  Seance seance = new Seance();
		seance.setStartDateTime(null);
	}

	
	// Status field test cover
	@Test(expected = IllegalArgumentException.class)
	public void testSetStatusShouldBeNotNull() {
	  Seance seance = new Seance();
		seance.setStatus(null);
	}

	@Test
	public void testSetStatusShouldBeCorrectl() {
	  Seance seance = new Seance();
		seance.setStatus(SeanceStatus.CANCEL);
		assertEquals(SeanceStatus.CANCEL, seance.getStatus());
	}

  // Seance[1] - [1]Room reference test cover
  @Test
  public void testSetRoomShouldBeCorrect() {
    Seance seance = new Seance();
    Room room = new Room();
    room.setName("White");
    seance.setRoom(room);
    assertEquals(room, seance.getRoom());
    assertEquals("White", seance.getRoom().getName());
  }

  @Test(expected = NullPointerException.class)
  public void testSetRoomShouldNotBeNull() {
    Seance seance = new Seance();
    seance.setRoom(null);
  }
}
