package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoomTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForEmptyName() {
		Room room = new Room();
		room.setName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseAnErrorForNullName() {
		Room room = new Room();
		room.setName(null);
	}

	@Test
	public void shouldSpecifyName() {
		Room room = new Room();
		room.setName("White");
		assertEquals("White", room.getName());
	}
}
