package com.sytoss.training.cinema.translator;

import com.sytoss.training.cinema.bom.Cinema;

public class CinemaTranslator {

  public Cinema fromDTO(String cinemaDTO) {
    return new Cinema(cinemaDTO);
  }

  public String toDTO(Cinema cinema) {
    return cinema.showName();
  }

}
