package com.sytoss.training.cinema.translator;

import com.sytoss.training.cinema.bom.Room;

public class RoomTranslator {

  public String toDTO(Room room) {
    return room.getName();
  }

  public Room fromDTO(String roomDTO) {
    return new Room(roomDTO);
  }
}
