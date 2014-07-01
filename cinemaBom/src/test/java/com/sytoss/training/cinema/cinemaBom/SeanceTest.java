package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
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
		assertEquals(seance.getStartDateTime(), calendar);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void  testSetStartDateTimeIsBiggerThanCurrent() {
		Calendar calendar = new GregorianCalendar().getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		seance.setStartDateTime(calendar);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void  testSetStartDateTimeIsNull() {
		seance.setStartDateTime(null);
	}

}
