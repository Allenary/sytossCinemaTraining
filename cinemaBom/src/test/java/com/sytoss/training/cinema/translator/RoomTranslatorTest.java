package com.sytoss.training.cinema.translator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sytoss.training.cinema.bom.Room;

public class RoomTranslatorTest {

  @Test
  public void shouldTranslateToDTO() {
    String roomDTO = new RoomTranslator().toDTO(new Room("red"));
    assertEquals("red", roomDTO);
  }

  @Test
  public void shouldTranslateFromDTO() {
    Room room = new RoomTranslator().fromDTO("blue");
    assertEquals("blue", room.getName());
  }
}
