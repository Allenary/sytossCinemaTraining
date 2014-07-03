package com.sytoss.training.cinema.cinemaBom;

public class Row {

	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		if (number <= 0) {
			throw new IllegalArgumentException(
					"Number should be bigger than zero.");
		}
		this.number = number;
	}

}
