package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class CinemaTest {
    
	@Test(expected = IllegalArgumentException.class)
	public void checkNameIsNotEmpty() {
		Cinema cinema = new Cinema();
		cinema.setName("");
	}
    
	@Test(expected = IllegalArgumentException.class)
	public void checkNameIsNotNull() {
		Cinema cinema = new Cinema();
		cinema.setName(null);
	}
	
	@Test
	public void checkNameSetCorrectly() {
		String cinemaName = "Kronverk";
		Cinema cinema = new Cinema();
		cinema.setName(cinemaName);
		assertEquals(cinema.getName(), cinemaName);
	}

	
	@Test
	public void checkAddressSetCorrectly() {
		String address = "Krasnoproletarskaya st., 16/2, Ent. 5, Moscow, 127473, Russian Federation";
		Cinema cinema = new Cinema();
		cinema.setAddress(address);
		assertEquals(cinema.getAddress(), address);
	}
	
	

	@Test(expected = IllegalArgumentException.class)
	public void checkAddressIsNotEmpty() {
		System.out.print("checkAddressIsNotEmpty");
		Cinema cinema = new Cinema();
		cinema.setAddress("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkAddressIsNotNull() {
		System.out.print("checkAddressIsNotNull");
		Cinema cinema = new Cinema();
		cinema.setAddress(null);
	}
}
