package com.sytoss.training.cinema.bom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sytoss.training.cinema.bom.Movie;

public class MovieTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForZeroDuration() {
		Movie movie = new Movie();
		movie.setDuration(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNegativeDuration() {
		Movie movie = new Movie();
		movie.setDuration(-8);
	}

	@Test
	public void shouldSpecifyDuration() {
		Movie movie = new Movie();
		movie.setDuration(120);
		assertEquals(120, movie.getDuration());
	}

	// Name field test cover
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForEmptyName() {
		Movie movie = new Movie();
		movie.setName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNullName() {
		Movie movie = new Movie();
		movie.setName(null);
	}

	@Test
	public void shouldSpecifyName() {
		Movie movie = new Movie();
		movie.setName("Titanic");
		assertEquals("Titanic", movie.getName());
	}

	// Description field test cover
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNullDescription() {
		Movie movie = new Movie();
		movie.setDescription(null);
	}

	public void shouldSetValidDescription() {
		Movie movie = new Movie();
		movie.setDescription("Disaster movie of 1997 year");
		assertEquals("Disaster movie of 1997 year", movie.getDescription());
	}
}
