package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CashofficeTest {

	@Test
	public void testNumberSetCorrectly() {
		Cashoffice cashoffice = new Cashoffice();
		cashoffice.setNumber(12);
		assertEquals(12, cashoffice.getNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNumberIsNotNegative() {
		Cashoffice cashoffice = new Cashoffice();
		cashoffice.setNumber(-7);
	}

	@Test
	public void testCinemaSetCorrectly() {
		Cashoffice cashoffice = new Cashoffice();
		Cinema cinema = new Cinema();
		cinema.setName("vasilisa");
		cinema.setAddress("kharkov");
		cashoffice.setCinema(cinema);
		assertEquals(cinema, cashoffice.getCinema());
		assertEquals("vasilisa", cashoffice.getCinema().getName());
		assertEquals("kharkov", cashoffice.getCinema().getAddress());
	}

	@Test(expected = NullPointerException.class)
	public void testCinemaIsNotNull() {
		Cashoffice cashoffice = new Cashoffice();
		cashoffice.setCinema(null);

	}
}
