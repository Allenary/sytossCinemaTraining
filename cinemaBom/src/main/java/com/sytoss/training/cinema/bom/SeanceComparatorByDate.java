package com.sytoss.training.cinema.bom;

import java.util.Comparator;

public class SeanceComparatorByDate implements Comparator<Seance> {

  public int compare(Seance o1, Seance o2) {
    Seance firstSeance = (Seance) o1;
    Seance secondSeance = (Seance) o2;
    return firstSeance.getStartDateTime().compareTo(secondSeance.getStartDateTime());
  }

}
