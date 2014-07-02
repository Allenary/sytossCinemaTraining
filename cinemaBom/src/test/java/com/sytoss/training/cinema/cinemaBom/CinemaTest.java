package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class CinemaTest {

	static Cinema cinema;

	@BeforeClass
	public static void initializeCinema() {
		cinema = new Cinema();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNameIsEmpty() {
		cinema.setName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNameIsNull() {
		cinema.setName(null);
	}

	@Test
	public void testSetNameCorrectl() {
		String cinemaName = "Kronverk";
		cinema.setName(cinemaName);
		assertEquals(cinemaName, cinema.getName());
	}

	@Test
	public void testSetAddressCorrectl() {
		String address = "Krasnoproletarskaya st., 16/2, Ent. 5, Moscow, 127473, Russian Federation";
		cinema.setAddress(address);
		assertEquals(address, cinema.getAddress());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetAddressIsEmpty() {
		cinema.setAddress("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetAddressIsNull() {
		cinema.setAddress(null);
	}

	@Test
	public void testAddMovie() {
		Movie movie = new Movie();
		movie.setName("Brave heart");
		cinema.addMovie(movie);
		assertTrue(cinema.showAllMovies().contains(movie));
		cinema.removeMovie(movie);
	}

	@Test(expected = NullPointerException.class)
	public void testAddMovieNull() {
		cinema.addMovie(null);
	}

}
