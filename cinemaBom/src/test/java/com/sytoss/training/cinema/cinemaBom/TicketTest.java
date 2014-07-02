package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicketTest {

	@Test
	public void testStatusSetCorrectly() {
		Ticket ticket = new Ticket();
		ticket.setStatus(TicketStatus.ENABLE);
		assertEquals(TicketStatus.ENABLE, ticket.getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStatusShouldNotBeNull() {
		Ticket ticket = new Ticket();
		ticket.setStatus(null);
	}

	@Test
	public void testPriceSetCorrectly() {
		Ticket ticket = new Ticket();
		ticket.setPrice(22.43);
		assertEquals(22.43, ticket.getPrice(), 0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testPriceShouldNotBeNegative() {
		Ticket ticket = new Ticket();
		ticket.setPrice(-5.53);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPriceShouldNotBeZero() {
		Ticket ticket = new Ticket();
		ticket.setPrice(0);
	}

	@Test
	public void testPlaceSetCorrectly() {
		Ticket ticket = new Ticket();
		Place place = new Place();
		place.setStatus(PlacesStatus.DISABLE);
		place.setNumber(72);
		ticket.setPlace(place);
		assertEquals(place, ticket.getPlace());
		assertEquals(72, ticket.getPlace().getNumber());
		assertEquals(PlacesStatus.DISABLE, ticket.getPlace().getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPlaceShouldNotBeNull() {
		Ticket ticket = new Ticket();
		ticket.setPlace(null);
	}

	@Test
	public void testSeanceSetCorrectly() {
		Ticket ticket = new Ticket();
		Seance seance = new Seance();
		seance.setStatus(SeanceStatus.CLOSED);
		ticket.setSeance(seance);
		assertEquals(seance, ticket.getSeance());
		assertEquals(SeanceStatus.CLOSED, ticket.getSeance().getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSeanceShouldNotBeNull() {
		Ticket ticket = new Ticket();
		ticket.setSeance(null);
	}

	@Test
	public void testRowSetCorrectly() {
		Ticket ticket = new Ticket();
		Row row = new Row();
		row.setNumber(17);
		ticket.setRow(row);
		assertEquals(row, ticket.getRow());
		assertEquals(17, ticket.getRow().getNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRowShouldNotBeNull() {
		Ticket ticket = new Ticket();
		ticket.setRow(null);
	}

}
