package com.sytoss.training.cinema.cinemaBom;

import java.util.Calendar;

public class Seance {

	// TODO: implement seance-room
	private Calendar startDateTime;

	private SeanceStatus status;

	public void setStartDateTime(Calendar calendar) {

		Calendar now = Calendar.getInstance();
		if (calendar == (null) || calendar.before(now)) {
			throw new IllegalArgumentException();
		}

		this.startDateTime = calendar;
	}

	public Object getStartDateTime() {
		return startDateTime;
	}

	public void setStatus(SeanceStatus status) {

		if (status == null) {
			throw new IllegalArgumentException();
		} else {
			this.status = status;
		}
	}

	public SeanceStatus getStatus() {
		return status;
	}
}
