package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.*;

import org.junit.Test;


public class RoomTest {

  // Name field test cover
  @Test(expected = IllegalArgumentException.class)
  public void testSetNameShouldNotBeEmpty() {
    Room room = new Room();
    room.setName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNameShouldNotBeNull() {
    Room room = new Room();
    room.setName(null);
  }

  @Test
  public void testSetNameShouldBeCorrect() {
    Room room = new Room();
    room.setName("White");
    assertEquals("White", room.getName());
  }
}
