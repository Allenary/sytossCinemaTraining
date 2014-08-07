package com.sytoss.training.cinema.translator;

import org.jdom2.Element;

import com.sytoss.training.cinema.bom.Room;

public class RoomTranslator {

  public String toDTO(Room room) {
    return room.getName();
  }

  public Room fromDTO(String roomDTO) {
    return new Room(roomDTO);
  }

  public Element toElement(Room room) {
    Element element = new Element("room");
    element.setText(room.getName());
    return element;
  }

  public Room fromDTO(Element element) {
    Room room = new Room(element.getText());
    return room;
  }
}
