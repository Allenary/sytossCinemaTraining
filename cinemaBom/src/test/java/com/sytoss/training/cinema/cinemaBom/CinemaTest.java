package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
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
		assertEquals(cinema.getName(), cinemaName);
	}

	
	@Test
	public void testSetAddressCorrectl() {
		String address = "Krasnoproletarskaya st., 16/2, Ent. 5, Moscow, 127473, Russian Federation";
		cinema.setAddress(address);
		assertEquals(cinema.getAddress(), address);
	}
	
	

	@Test(expected = IllegalArgumentException.class)
	public void testSetAddressIsEmpty() {
		System.out.print("checkAddressIsNotEmpty");
		cinema.setAddress("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetAddressIsNull() {
		System.out.print("checkAddressIsNotNull");
		cinema.setAddress(null);
	}
}
