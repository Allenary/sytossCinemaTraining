package com.sytoss.training.cinema.bom;

import org.apache.commons.lang.StringUtils;

public class Movie {

  private String name;

  private String description;

  private int duration;

  public Movie(String name) {
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("Name shouldn't be NULL or empty.");
    }
    this.name = name;

  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (description == null) {
      throw new IllegalArgumentException("Description shouldn't be NULL.");
    }
    this.description = description;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    if (duration <= 0) {
      throw new IllegalArgumentException("Duration should be bigger than zero.");
    }
    this.duration = duration;
  }
}
