package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CinemaTest {

  // Name field test cover
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameShouldNotBeEmpty() {
		Cinema cinema = new Cinema();
		cinema.setName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNameShouldNotBeNull() {
		Cinema cinema = new Cinema();
		cinema.setName(null);
	}

	@Test
	public void testSetNameShouldBeCorrect() {
		Cinema cinema = new Cinema();
		cinema.setName("Kronverk");
		assertEquals("Kronverk", cinema.getName());
	}

	
	// Address field test cover
	@Test
	public void testSetAddressShouldBeCorrect() {
		Cinema cinema = new Cinema();
		cinema.setAddress("Krasnoproletarskaya st., 16/2, Ent. 5, Moscow, 127473, Russian Federation");
		assertEquals(
				"Krasnoproletarskaya st., 16/2, Ent. 5, Moscow, 127473, Russian Federation",
				cinema.getAddress());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetAddressShouldNotBeEmpty() {
		Cinema cinema = new Cinema();
		cinema.setAddress("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetAddressShouldNotBeNull() {
		Cinema cinema = new Cinema();
		cinema.setAddress(null);
	}

	// Cinema[1] - [N]Movies reference test cover
	// TODO: create method exists for movie to encapsulate changes
	@Test
	public void testAddMovieShouldBeCorrect() {
		Movie movie = new Movie();
		Cinema cinema = new Cinema();
		movie.setName("Brave heart");
		cinema.addMovie(movie);
		assertTrue(cinema.showAllMovies().contains(movie));
	}

	@Test(expected = NullPointerException.class)
	public void testAddMovieShouldNotBeNull() {
		Cinema cinema = new Cinema();
		cinema.addMovie(null);
	}

}
