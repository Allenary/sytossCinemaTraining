package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MovieTest {
  
  // Duration field test cover
	@Test(expected = IllegalArgumentException.class)
	public void testSetDurationShouldBeBiggerThanZero() {
		Movie movie = new Movie();
		movie.setDuration(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDurationShouldNotBeNegative() {
		Movie movie = new Movie();
		movie.setDuration(-8);
	}

	@Test
	public void testSetDurationShouldBeCorrect() {
		Movie movie = new Movie();
		movie.setDuration(120);
		assertEquals(120, movie.getDuration());
	}

	
	// Name field test cover
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameShouldNotBeEmpty() {
		Movie movie = new Movie();
		movie.setName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNameShouldNotBeNull() {
		Movie movie = new Movie();
		movie.setName(null);
	}

	@Test
	public void testSetNameShouldBeCorrect() {
		Movie movie = new Movie();
		movie.setName("Titanic");
		assertEquals("Titanic", movie.getName());
	}
	
	
  // Description field test cover
	 @Test(expected = IllegalArgumentException.class)
	  public void testSetDescriptionShouldNotBeNull() {
	    Movie movie = new Movie();
	    movie.setDescription(null);
	  }	
	
	public void testSetDescriptionShouldBeCorrect() {
    Movie movie = new Movie();
    movie.setDescription("Disaster movie of 1997 year");
    assertEquals("Disaster movie of 1997 year", movie.getDescription());
  }
}



