package com.sytoss.training.cinema.translator;

import org.jdom2.Element;

import com.sytoss.training.cinema.bom.Movie;

public class MovieTranslator {

  public Movie fromDTO(String movieDTO) {
    return new Movie(movieDTO);
  }

  public String toDTO(Movie movie) {
    return movie.getName();
  }

  public Element toElement(Movie movie) {
    Element element = new Element("movie");
    element.setText(movie.getName());
    return element;
  }
}
