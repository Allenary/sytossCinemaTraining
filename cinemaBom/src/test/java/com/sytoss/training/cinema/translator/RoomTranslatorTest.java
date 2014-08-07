package com.sytoss.training.cinema.translator;

import static org.junit.Assert.assertEquals;

import org.jdom2.Element;
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

  @Test
  public void shouldTranslateToElement() {
    Element roomElement = new RoomTranslator().toElement(new Room("Green"));
    assertEquals("room", roomElement.getName());
    assertEquals("Green", roomElement.getText());
  }

  @Test
  public void shouldTranslateFromDTOElement() {
    Element element = new Element("room");
    element.setText("red");
    Room room = new RoomTranslator().fromDTO(element);
    assertEquals("red", room.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaiseExceptionWhenEmptyName() {
    Element element = new Element("room");
    element.setText("");
    new RoomTranslator().fromDTO(element);
  }
}
