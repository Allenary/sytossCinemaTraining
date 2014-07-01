package com.sytoss.training.cinema.cinemaBom;

public class Cinema {

	private String name;
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address == null || address.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			this.address = address;
		}
	}

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
