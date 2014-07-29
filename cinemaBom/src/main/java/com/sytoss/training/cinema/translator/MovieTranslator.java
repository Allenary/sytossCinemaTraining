package com.sytoss.training.cinema.translator;

import com.sytoss.training.cinema.bom.Movie;

public class MovieTranslator {

  public Movie fromDTO(String movieDTO) {
    return new Movie(movieDTO);
  }

  public String toDTO(Movie movie) {
    return movie.getName();
  }
}
