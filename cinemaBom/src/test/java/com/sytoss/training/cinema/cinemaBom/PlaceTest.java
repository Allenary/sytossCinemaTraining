package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlaceTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNegativeNumber() {
		Place place = new Place();
		place.setNumber(-8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForZeroNumber() {
		Place place = new Place();
		place.setNumber(0);
	}

	@Test
	public void shouldSpecifyNumber() {
		Place place = new Place();
		place.setNumber(12);
		assertEquals(12, place.getNumber());
	}

}
