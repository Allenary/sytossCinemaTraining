package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlaceTest {
  
  // Status field test cover
	@Test(expected = IllegalArgumentException.class)
	public void testSetStatusShouldNotBeNull() {
		Place place = new Place();
		place.setStatus(null);
	}

	@Test
	public void testSetStatusShouldBeCorrect() {
		Place place = new Place();
		place.setStatus(PlacesStatus.ENABLE);
		assertEquals(PlacesStatus.ENABLE, place.getStatus());
	}

	
	// Number field test cover
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
	public void testSetNumberShouldBeCorrect() {
		Place place = new Place();
		place.setNumber(12);
		assertEquals(12, place.getNumber());
	}

}
