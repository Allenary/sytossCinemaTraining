package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RowTest {

  // Number field test cover
	@Test
	public void testSetNumberShouldBeCorrect() {
		Row row = new Row();
		row.setNumber(4);
		assertEquals(4, row.getNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberShouldNotBeNegative() {
		Row row = new Row();
		row.setNumber(-7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberShouldNotBeZero() {
		Row row = new Row();
		row.setNumber(0);
	}

	  
	// Status field test cover
	@Test
	public void testSetStatusShouldBeCorrect() {
		Row row = new Row();
		row.setStatus(PlacesStatus.ENABLE);
		assertEquals(PlacesStatus.ENABLE, row.getStatus());
	}

	@Test(expected = NullPointerException.class)
	public void testSetStatusShouldNotBeNull() {
		Row row = new Row();
		row.setStatus(null);
	}
}
