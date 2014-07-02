package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;

public class SeanceTest {

	static Seance seance;

	@BeforeClass
	public static void initializeSeance() {
		seance = new Seance();
	}

	@Test
	public void testSetStartDateTimeCorrect() {
		Calendar calendar = new GregorianCalendar(2014, 6, 22);
		seance.setStartDateTime(calendar);
		assertEquals(calendar, seance.getStartDateTime());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStartDateTimeIsBiggerThanCurrent() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		seance.setStartDateTime(calendar);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStartDateTimeIsNull() {
		seance.setStartDateTime(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStatusIsNull() {
		seance.setStatus(null);
	}

	@Test
	public void testSetStatusSetCorrectly() {
		seance.setStatus(SeanceStatus.CANCEL);
		assertEquals(SeanceStatus.CANCEL, seance.getStatus());
	}

}
