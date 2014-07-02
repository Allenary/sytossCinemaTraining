package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MovieTest {

	// TODO: test for movie description field
	// TODO: tests naming
	static Movie movie;

	@Test(expected = IllegalArgumentException.class)
	public void testSetDurationIsBiggerThanZero() {
		Movie movie = new Movie();
		movie.setDuration(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDurationIsNotNegative() {
		Movie movie = new Movie();
		movie.setDuration(-8);
	}

	@Test
	public void testSetDurrationCorrect() {
		Movie movie = new Movie();
		movie.setDuration(120);
		assertEquals(120, movie.getDuration());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNameIsNotEmpty() {
		Movie movie = new Movie();
		movie.setName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNameIsNotNull() {
		Movie movie = new Movie();
		movie.setName(null);
	}

	@Test
	public void testSetNameIsCorrect() {
		Movie movie = new Movie();
		movie.setName("Titanic");
		assertEquals("Titanic", movie.getName());
	}
}
