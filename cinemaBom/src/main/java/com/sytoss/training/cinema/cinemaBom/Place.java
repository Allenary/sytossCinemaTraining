package com.sytoss.training.cinema.cinemaBom;

public class Place {

	int number;

	PlacesStatuses status;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		if (number <= 0) {
			throw new IllegalArgumentException();
		}
		this.number = number;
	}

	public PlacesStatuses getStatus() {
		return status;
	}

	public void setStatus(PlacesStatuses status) {

		if (status == null) {
			throw new IllegalArgumentException();
		} else {
			this.status = status;
		}

	}

}
