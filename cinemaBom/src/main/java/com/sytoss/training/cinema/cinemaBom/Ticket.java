package com.sytoss.training.cinema.cinemaBom;

public class Ticket {

	private double price;
	private TicketStatus status;
	private Place place;
	private Seance seance;
	private Row row;

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		if (seance == null) {
			throw new IllegalArgumentException("Seance shouldn't be NULL.");
		}
		this.seance = seance;
	}

	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		if (row == null) {
			throw new IllegalArgumentException("Row shouldn't be NULL.");
		}
		this.row = row;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price <= 0) {
			throw new IllegalArgumentException(
					"Price should be bigger than zero");
		}
		this.price = price;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("Status shouldn't be NULL.");
		}
		this.status = status;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		if (place == null) {
			throw new IllegalArgumentException("Place shouldn't be NULL.");
		}
		this.place = place;
	}

}
