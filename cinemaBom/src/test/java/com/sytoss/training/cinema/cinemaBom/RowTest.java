package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RowTest {

	@Test
	public void testNumberSetCorrectly() {
		Row row = new Row();
		row.setNumber(4);
		assertEquals(4, row.getNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNumberShouldNotBeNegative() {
		Row row = new Row();
		row.setNumber(-7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNumberShouldNotBeZero() {
		Row row = new Row();
		row.setNumber(0);
	}

	@Test
	public void testStatusSetCorrectly() {
		Row row = new Row();
		row.setStatus(PlacesStatus.ENABLE);
		assertEquals(PlacesStatus.ENABLE, row.getStatus());
	}

	@Test(expected = NullPointerException.class)
	public void testStatusShouldNotBeNull() {
		Row row = new Row();
		row.setStatus(null);
	}
}
