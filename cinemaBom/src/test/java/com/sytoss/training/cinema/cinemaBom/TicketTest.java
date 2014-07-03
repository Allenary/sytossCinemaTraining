package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicketTest {

	@Test
	public void shouldSpecifyStatus() {
		Ticket ticket = new Ticket();
		ticket.setStatus(TicketStatus.ENABLE);
		assertEquals(TicketStatus.ENABLE, ticket.getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNullStatus() {
		Ticket ticket = new Ticket();
		ticket.setStatus(null);
	}

	@Test
	public void shouldSpecifyPrice() {
		Ticket ticket = new Ticket();
		ticket.setPrice(22.43);
		assertEquals(22.43, ticket.getPrice(), 0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNegativePrice() {
		Ticket ticket = new Ticket();
		ticket.setPrice(-5.53);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForZeroPrice() {
		Ticket ticket = new Ticket();
		ticket.setPrice(0);
	}

	// Ticket[1] - [1]Place reference test cover
	@Test
	public void shouldSpecifyPlaceInstance() {
		Ticket ticket = new Ticket();
		Place place = new Place();
		place.setNumber(72);
		ticket.setPlace(place);
		assertEquals(place, ticket.getPlace());
		assertEquals(72, ticket.getPlace().getNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNullPlaceInstance() {
		Ticket ticket = new Ticket();
		ticket.setPlace(null);
	}

	// Ticket[1] - [1]Seance reference test cover
	@Test
	public void testSetSeanceShouldBeCorrect() {
		Ticket ticket = new Ticket();
		Seance seance = new Seance();
		seance.setStatus(SeanceStatus.CLOSED);
		ticket.setSeance(seance);
		assertEquals(seance, ticket.getSeance());
		assertEquals(SeanceStatus.CLOSED, ticket.getSeance().getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSeanceShouldNotBeNull() {
		Ticket ticket = new Ticket();
		ticket.setSeance(null);
	}

	// Ticket[1] - [1]Raw reference test cover
	@Test
	public void testSetRowShouldBeCorrect() {
		Ticket ticket = new Ticket();
		Row row = new Row();
		row.setNumber(17);
		ticket.setRow(row);
		assertEquals(row, ticket.getRow());
		assertEquals(17, ticket.getRow().getNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetRowShouldNotBeNull() {
		Ticket ticket = new Ticket();
		ticket.setRow(null);
	}
}
