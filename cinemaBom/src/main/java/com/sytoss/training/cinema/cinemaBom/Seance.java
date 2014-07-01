package com.sytoss.training.cinema.cinemaBom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Seance {

  private Calendar startDateTime;

  private String status;

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

  public void setStatus(String status) {
    ArrayList possibleStatus = new ArrayList(Arrays.asList("opened", "closed", "cancel"));
    if (status == null || status == "" || !possibleStatus.contains(status.toLowerCase())) {
      throw new IllegalArgumentException();
    }else {
      this.status = status;
    }
  }
  
  public String getStatus(){
    return status;
  }
}
