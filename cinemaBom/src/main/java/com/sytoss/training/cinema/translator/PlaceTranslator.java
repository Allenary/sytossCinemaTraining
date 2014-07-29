package com.sytoss.training.cinema.translator;

import com.sytoss.training.cinema.bom.Place;

public class PlaceTranslator {

  public String toDTO(Place place) {
    return Integer.toString(place.getNumber());
  }

  public Place fromDTO(String placeDTO) {
    return new Place(Integer.parseInt(placeDTO));
  }
}
