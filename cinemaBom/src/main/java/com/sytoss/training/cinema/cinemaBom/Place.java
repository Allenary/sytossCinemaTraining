package com.sytoss.training.cinema.cinemaBom;

public class Place {

	private int number;

	private PlacesStatus status;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		if (number <= 0) {
			throw new IllegalArgumentException();
		}
		this.number = number;
	}

	public PlacesStatus getStatus() {
		return status;
	}

	public void setStatus(PlacesStatus status) {
		if (status == null) {
			throw new IllegalArgumentException();
		} else {
			this.status = status;
		}
	}

}
