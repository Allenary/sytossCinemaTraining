package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TicketTest {

	static Ticket ticket;

	@BeforeClass
	public static void initializeTicket() {
		ticket = new Ticket();
	}

	@Test
	public void testSetStatusCorrect() {
		TicketStatuses status = TicketStatuses.ENABLE;
		ticket.setStatus(status);
		assertEquals(status, ticket.getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStatusNull() {
		ticket.setStatus(null);
	}

	@Test
	public void testSetPriceCorrect() {
		double price = 22.43;
		ticket.setPrice(price);
		assertTrue(price == ticket.getPrice());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPriceNegative() {
		ticket.setPrice(-5.53);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPriceZero() {
		ticket.setPrice(0);
	}

	@Test
	public void testSetPlaceCorrect() {
		Place place = new Place();
		ticket.setPlace(place);
		assertEquals(place, ticket.getPlace());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPlaceNull() {
		ticket.setPlace(null);
	}

	@Test
	public void testSetSeanceCorrect() {
		Seance seance = new Seance();
		ticket.setSeance(seance);
		assertEquals(seance, ticket.getSeance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSeanceNull() {
		ticket.setSeance(null);
	}

	@Test
	public void testSetRowCorrect() {
		Row row = new Row();
		ticket.setRow(row);
		assertEquals(row, ticket.getRow());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetRowNull() {
		ticket.setRow(null);
	}
}
