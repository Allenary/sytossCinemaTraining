package com.sytoss.training.cinema.bom;

public class Room {

	private String name;

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(
					"Name shouldn't be NULL or empty.");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
