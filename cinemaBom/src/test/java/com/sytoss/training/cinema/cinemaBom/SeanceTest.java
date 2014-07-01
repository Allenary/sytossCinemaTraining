package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class SeanceTest {

	@Test
	public void checkCorrectDateTime() {
		Seance seance = new Seance();
		Calendar calendar = new GregorianCalendar(2014, 6, 22);
		seance.setStartDateTime(calendar);
		assertEquals(seance.getStartDateTime(), calendar);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkStartDateTimeIsBiggerThanCurrent() {
		Seance seance = new Seance();
		//Date currentDate = new Date();
		Calendar calendar = new GregorianCalendar().getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		seance.setStartDateTime(calendar);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkDateTimeNotNull() {
		Seance seance = new Seance();
		seance.setStartDateTime(null);
	}

}
