package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CinemaTest {

	@Test(expected = IllegalArgumentException.class)
	public void testNameShouldNotBeEmpty() {
		Cinema cinema = new Cinema();
		cinema.setName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNameShouldNotBeNull() {
		Cinema cinema = new Cinema();
		cinema.setName(null);
	}

	@Test
	public void testNameSetCorrectly() {
		Cinema cinema = new Cinema();
		cinema.setName("Kronverk");
		assertEquals("Kronverk", cinema.getName());
	}

	@Test
	public void testAddressSetCorrectly() {
		Cinema cinema = new Cinema();
		cinema.setAddress("Krasnoproletarskaya st., 16/2, Ent. 5, Moscow, 127473, Russian Federation");
		assertEquals(
				"Krasnoproletarskaya st., 16/2, Ent. 5, Moscow, 127473, Russian Federation",
				cinema.getAddress());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddressIsNotEmpty() {
		Cinema cinema = new Cinema();
		cinema.setAddress("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddressIsNotNull() {
		Cinema cinema = new Cinema();
		cinema.setAddress(null);
	}

	// TODO: create method exists for movie to encapsulate changes
	@Test
	public void testAddMovie() {
		Movie movie = new Movie();
		Cinema cinema = new Cinema();
		movie.setName("Brave heart");
		cinema.addMovie(movie);
		assertTrue(cinema.showAllMovies().contains(movie));
	}

	@Test(expected = NullPointerException.class)
	public void testAddMovieNull() {
		Cinema cinema = new Cinema();
		cinema.addMovie(null);
	}

}
