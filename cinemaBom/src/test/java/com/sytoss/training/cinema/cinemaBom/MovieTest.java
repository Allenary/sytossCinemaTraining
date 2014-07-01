package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovieTest {

	@Test(expected = IllegalArgumentException.class)
	public void checkDurationIsBiggerThanZero() {
		Movie movie = new Movie();
		movie.setDuration(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void checkDurationIsNotNegative() {
		Movie movie = new Movie();
		movie.setDuration(-8);
	}

	@Test
	public void checkCorrectDuration() {
		int movieDur = 120;
		Movie movie = new Movie();
		movie.setDuration(120);
		assertEquals(movie.getDuration(), movieDur);
	}

	@Test(expected = IllegalArgumentException.class)
	public void checkNameIsNotEmpty() {
		Movie movie = new Movie();
		movie.setName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void checkNameIsNotNull() {
		Movie movie = new Movie();
		movie.setName(null);
	}

	@Test
	public void checkNameSetCorrectly() {
		String movieName = "Titanic";
		Movie movie = new Movie();
		movie.setName(movieName);
		assertEquals(movie.getName(), movieName);
	}

}
