package com.sytoss.training.cinema.cinemaBom;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

	// TODO: implement dependencies with 1-to-many multiplicity
	private String name;
	private String address;
	private List<Movie> movies;
	private List<Cashoffice> cashoffices;
	private List<Room> rooms;
	private List<Seance> seances;

	public Cinema() {
		movies = new ArrayList<Movie>();
		cashoffices = new ArrayList<Cashoffice>();
		rooms = new ArrayList<Room>();
	}
	

	public List<Movie> showAllMovies() {
		return movies;
	}

	public void addMovie(Movie movie) {
		if (movie == null) {
			throw new NullPointerException("Null movie can't be added!");
		}
		movies.add(movie);
	}

	public boolean removeMovie(Movie movie) {
		return movies.remove(movie);

	}

	public List<Cashoffice> showAllCashoffices() {
		return cashoffices;
	}

	public void addCashoffice(Cashoffice cashoffice) {
		cashoffices.add(cashoffice);
	}

	public List<Room> showAllRooms() {
		return rooms;
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

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
