package com.sytoss.training.cinema.domainservice;

import java.io.File;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Movie;
import com.sytoss.training.cinema.bom.Place;
import com.sytoss.training.cinema.bom.Room;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.translator.CashOfficeTranslator;
import com.sytoss.training.cinema.translator.CinemaTranslator;
import com.sytoss.training.cinema.translator.MovieTranslator;
import com.sytoss.training.cinema.translator.PlaceTranslator;
import com.sytoss.training.cinema.translator.RoomTranslator;
import com.sytoss.training.cinema.translator.RowTranslator;
import com.sytoss.training.cinema.translator.SeanceTranslator;

public abstract class AbstractReader implements IReader {

  protected Map<String, Cinema> mapCinemas;

  public AbstractReader() {
    mapCinemas = new HashMap<String, Cinema>();
  }

  public boolean isValidXML(String fileName) {
    try {
      File xsdFile = new File(getClass().getResource("/cinemas.xsd").toURI());
      Source xmlFile = new StreamSource(new File(fileName));
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema;
      schema = schemaFactory.newSchema(xsdFile);
      Validator validator = schema.newValidator();
      validator.validate(xmlFile);
      return true;
    } catch (Exception e) {
      return false;
    }

  }

  protected Cinema findOrCreateNewCinema(String cinemaName) {
    Cinema cinema = mapCinemas.get(cinemaName);
    if (cinema == null) {
      cinema = new CinemaTranslator().fromDTO(cinemaName);
      mapCinemas.put(cinemaName, cinema);
    }
    return cinema;
  }

  protected CashOffice findOrCreateNewCO(String coID, Cinema cinema) {
    CashOffice cashOffice;
    Iterator<CashOffice> cashOffices = cinema.cashOfficeIterator();
    while (cashOffices.hasNext()) {
      cashOffice = cashOffices.next();
      if (cashOffice.getNumber() == Integer.parseInt(coID)) {
        return cashOffice;
      }
    }
    cashOffice = new CashOfficeTranslator().fromDTO(coID);
    cinema.addCashOffice(cashOffice);
    return cashOffice;
  }

  protected Seance findOrCreateNewSeance(String startDateTime, Cinema cinema, Room room, String dateFormat) throws ParseException {
    Seance seance;
    Seance seanceT = new SeanceTranslator(dateFormat).fromDTO(startDateTime);
    Iterator<Seance> seances = cinema.seanceIterator();
    while (seances.hasNext()) {
      seance = seances.next();
      if (room.equals(seance.getRoom()) && seanceT.getStartDateTime().equals(seance.getStartDateTime())) {
        return seance;
      }
    }
    seanceT.setRoom(room);
    cinema.addSeance(seanceT);
    return seanceT;
  }

  protected Room findOrCreateNewRoom(String roomName, Cinema cinema) {
    Iterator<Room> rooms = cinema.roomIterator();
    Room room;
    while (rooms.hasNext()) {
      room = rooms.next();
      if (room.getName().equals(roomName)) {
        return room;
      }
    }
    room = new RoomTranslator().fromDTO(roomName);
    cinema.addRoom(room);
    return room;
  }

  protected Movie findOrCreateNewMovie(String movieName, Cinema cinema) {
    Iterator<Movie> movies = cinema.movieIterator();
    Movie movie;
    while (movies.hasNext()) {
      movie = movies.next();
      if (movie.getName().equals(movieName)) {
        return movie;
      }
    }
    movie = new MovieTranslator().fromDTO(movieName);
    cinema.addMovie(movie);
    return movie;
  }

  protected Row findOrCreateNewRow(String rowNumber, Room room) {
    Row row;
    Iterator<Row> rows = room.getAllRows();
    while (rows.hasNext()) {
      row = rows.next();
      if (row.getNumber() == Integer.parseInt(rowNumber)) {
        return row;
      }
    }
    row = new RowTranslator().fromDTO(rowNumber);
    room.addRow(row);
    return row;
  }

  protected Place findOrCreateNewPlace(String placeNumber, Row row) {
    Place place;
    Iterator<Place> places = row.getAllPlaces();
    while (places.hasNext()) {
      place = places.next();
      if (place.getNumber() == Integer.parseInt(placeNumber)) {
        return place;
      }
    }
    place = new PlaceTranslator().fromDTO(placeNumber);
    row.addPlace(place);
    return place;
  }

  public abstract Map<String, Cinema> read(List<String> inputFileNames);

}
