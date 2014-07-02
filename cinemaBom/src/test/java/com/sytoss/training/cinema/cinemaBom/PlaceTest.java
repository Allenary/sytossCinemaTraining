package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlaceTest {

	@Test(expected = IllegalArgumentException.class)
	public void testStatusShouldNotBeNull() {
		Place place = new Place();
		place.setStatus(null);
	}

	@Test
	public void testStatusSetCorrectly() {
		Place place = new Place();
		place.setStatus(PlacesStatus.ENABLE);
		assertEquals(PlacesStatus.ENABLE, place.getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberShouldNotBeNegative() {
		Place place = new Place();
		place.setNumber(-8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberShouldNotBeZero() {
		Place place = new Place();
		place.setNumber(0);
	}

	@Test
	public void testNumberSetCorrect() {
		Place place = new Place();
		place.setNumber(12);
		assertEquals(12, place.getNumber());
	}

}
