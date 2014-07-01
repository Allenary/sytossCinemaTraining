package com.sytoss.training.cinema.cinemaBom;

import java.util.Calendar;

public class Seance {

  private Calendar startDateTime;

  public void setStartDateTime(Calendar calendar) {

    Calendar now = Calendar.getInstance();
    if (calendar == (null) || calendar.before(now)) {
      throw new IllegalArgumentException();
    }
    this.startDateTime = calendar;
  }

  public Object getStartDateTime() {
    return startDateTime;
  }

}
