package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class PlaceTest {

	static Place place;

	@BeforeClass
	public static void initializePlace() {
		place = new Place();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStatusIsNull() {
		place.setStatus(null);
	}

	@Test
	public void testSetStatusCorrect() {
		PlacesStatuses status = PlacesStatuses.DISABLE;
		place.setStatus(status);
		assertEquals(status, place.getStatus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberIsNegative() {
		place.setNumber(-8);
	}

	@Test
	public void testSetNumberCorrect() {
		int placeNum = 12;
		place.setNumber(placeNum);
		assertEquals(placeNum, place.getNumber());
	}

}
