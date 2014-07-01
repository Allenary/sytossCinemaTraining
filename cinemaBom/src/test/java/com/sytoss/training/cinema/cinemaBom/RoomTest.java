package com.sytoss.training.cinema.cinemaBom;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class RoomTest {

  static Room room;

  @BeforeClass
  public static void initializeRoom() {
    room = new Room();
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testSetNameIsEmpty() {
    room.setName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNameIsNull() {
    room.setName(null);
  }

  @Test
  public void testSetNameCorrect() {
    String roomName = "White";
    room.setName(roomName);
    assertEquals(roomName, room.getName());
  }
}
