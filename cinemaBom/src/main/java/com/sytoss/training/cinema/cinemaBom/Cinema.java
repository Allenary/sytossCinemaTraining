package com.sytoss.training.cinema.cinemaBom;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

	// TODO: implement dependencies with 1-to-many multiplicity
	private String name;
	private String address;
	private List<Movie> movies;

	public Cinema() {
		movies = new ArrayList<Movie>();

	}

	public List<Movie> showAllMovies() {
		return movies;
	}

	public void addMovie(Movie movie) {
		if (movie == null) {
			throw new IllegalArgumentException("Movie shouldn't be NULL.");
		}
		movies.add(movie);
	}

	public boolean removeMovie(Movie movie) {
		return movies.remove(movie);

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address == null || address.isEmpty()) {
			throw new IllegalArgumentException(
					"Address shouldn't be NULL or empty.");
		} else {
			this.address = address;
		}
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(
					"Name shouldn't be NULL or empty.");
		} else {
			this.name = name;
		}
	}

	public String getName() {
		return name;
	}

}
