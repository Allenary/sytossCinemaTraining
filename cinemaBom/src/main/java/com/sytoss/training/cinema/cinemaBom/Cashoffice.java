package com.sytoss.training.cinema.cinemaBom;

public class Cashoffice {
	private int number;
	private Cinema cinema;

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		if (cinema == null) {
			throw new IllegalArgumentException(
					"Cinema object should not be set NULL.");
		}
		this.cinema = cinema;
	}

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
