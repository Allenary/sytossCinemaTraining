package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlaceTest {

	@Test(expected = IllegalArgumentException.class)
	public void testSetStatusIsNull() {
		Place place = new Place();
		place.setStatus(null);
	}

	@Test
	public void testSetStatusCorrect() {
		Place place = new Place();
		place.setStatus(PlacesStatuses.ENABLE);
		assertEquals(PlacesStatuses.ENABLE, place.getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberIsNegative() {
		Place place = new Place();
		place.setNumber(-8);
	}

	@Test
	public void testSetNumberCorrect() {
		Place place = new Place();
		place.setNumber(12);
		assertEquals(12, place.getNumber());
	}

}
