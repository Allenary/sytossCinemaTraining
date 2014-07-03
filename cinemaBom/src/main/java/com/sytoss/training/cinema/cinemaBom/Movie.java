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
			throw new IllegalArgumentException(
					"Name shouldn't be NULL or empty.");
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
			throw new IllegalArgumentException(
					"Duration should be bigger than zero.");
		}
		this.duration = duration;
	}

}
