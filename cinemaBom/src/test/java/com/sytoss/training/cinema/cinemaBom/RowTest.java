package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RowTest {

	@Test
	public void shouldSpecifyNumber() {
		Row row = new Row();
		row.setNumber(4);
		assertEquals(4, row.getNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNegativeNumber() {
		Row row = new Row();
		row.setNumber(-7);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForZeroNumber() {
		Row row = new Row();
		row.setNumber(0);
	}

}
