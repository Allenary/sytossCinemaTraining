package com.sytoss.training.cinema.cinemaBom;

public class Movie {

	private String name;
	private String description;
	/*
	 * duration counted in minutes.
	 */
	private int duration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
	  if(description == null){
	    throw new IllegalArgumentException();
	  }
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if (duration <= 0) {
			throw new IllegalArgumentException();
		}
		this.duration = duration;
	}

}
