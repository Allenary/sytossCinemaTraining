package com.sytoss.training.cinema.cinemaBom;

public class Room {

	String name;

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			this.name = name;
		}

	}

	public String getName() {
		return name;
	}

}
