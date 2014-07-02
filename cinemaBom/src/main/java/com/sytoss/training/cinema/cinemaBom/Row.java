package com.sytoss.training.cinema.cinemaBom;

public class Row {

	private PlacesStatus status;

	private int number;

	public PlacesStatus getStatus() {
		return status;
	}

	public void setStatus(PlacesStatus status) {
		if (status == null) {
			throw new NullPointerException();
		}
		this.status = status;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		if (number <= 0) {
			throw new IllegalArgumentException();
		}
		this.number = number;
	}

}
