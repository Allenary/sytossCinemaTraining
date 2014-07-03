package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CashofficeTest {

	// Number field test cover
	@Test
	public void shouldSpecifyNumber() {
		Cashoffice cashoffice = new Cashoffice();
		cashoffice.setNumber(12);
		assertEquals(12, cashoffice.getNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForZeroNumber() {
		Cashoffice cashoffice = new Cashoffice();
		cashoffice.setNumber(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNegativeNumber() {
		Cashoffice cashoffice = new Cashoffice();
		cashoffice.setNumber(-7);
	}

	// CashOffice[1] - [1]Cinema reference test cover
	@Test
	public void shouldSpecifyCinemaInstance() {
		Cashoffice cashoffice = new Cashoffice();
		Cinema cinema = new Cinema();
		cinema.setName("vasilisa");
		cinema.setAddress("kharkov");
		cashoffice.setCinema(cinema);
		assertEquals(cinema, cashoffice.getCinema());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNullCinemaInstance() {
		Cashoffice cashoffice = new Cashoffice();
		cashoffice.setCinema(null);
	}
}
