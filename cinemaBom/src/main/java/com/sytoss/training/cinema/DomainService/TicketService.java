package com.sytoss.training.cinema.domainservice;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Movie;
import com.sytoss.training.cinema.bom.Place;
import com.sytoss.training.cinema.bom.Room;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.FileSystemConnector;
import com.sytoss.training.cinema.translator.CashOfficeTranslator;
import com.sytoss.training.cinema.translator.CinemaTranslator;
import com.sytoss.training.cinema.translator.MovieTranslator;
import com.sytoss.training.cinema.translator.PlaceTranslator;
import com.sytoss.training.cinema.translator.RoomTranslator;
import com.sytoss.training.cinema.translator.RowTranslator;
import com.sytoss.training.cinema.translator.SeanceTranslator;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class TicketService {

  private IXmlWriter xmlWriter;

  private IXmlReader xmlReader;

  private Map<String, Cinema> mapCinemas;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public TicketService() {
    mapCinemas = new HashMap<String, Cinema>();
    xmlWriter = new JdomXmlWriter();
    xmlReader = new StaxReader();
  }

  public List<Ticket> readFromCSVFiles(List<String> fileNames) {

    List<String> csvRows = new ArrayList<String>();
    List<Ticket> allTickets = new ArrayList<Ticket>();
    List<Ticket> ticketsIn1File;
    boolean isFileSkipped;
    for (String inputFile : fileNames) {
      isFileSkipped = false;
      logger.info("start with processing file: " + inputFile);
      ticketsIn1File = new ArrayList<Ticket>();
      try {
        csvRows = new FileSystemConnector().read(inputFile);
        logger.debug("Rows found: " + csvRows.size());
        for (String row : csvRows) {
          try {
            ticketsIn1File.add(getTicketFromCSV((new CsvParser(new SplitSplitStringStrategy()).parse(row))));

            logger.debug("row '" + row + "' parsed");
          } catch (Exception e) {
            logger.warn("file " + inputFile + " was skipped. Reason: Corrupted data. Row: " + row);
            e.printStackTrace();
            isFileSkipped = true;
          }
        }
      } catch (IOException e1) {
        isFileSkipped = true;
        logger.error(e1.getMessage());
      }
      if ( !isFileSkipped && equalsCashOfficeID(ticketsIn1File)) {
        allTickets.addAll(ticketsIn1File);
        logger.info("file added to merge: " + inputFile);
      }

    }
    logger.debug("count tickets=" + allTickets.size());
    return allTickets;

  }

  private void writeInCSV(List<Ticket> tickets, String fileNameDestination) {
    List<String> csvStrings = new ArrayList<String>();
    try {
      for (Ticket ticket : tickets) {
        csvStrings.add(new CsvParser(new SplitSplitStringStrategy()).deParse((new TicketTranslator().toDTO(ticket))));
      }
      new FileSystemConnector().write(csvStrings, fileNameDestination);
    } catch (Exception e) {
      logger.error(e.getMessage());
      e.printStackTrace();
    }
  }

  private boolean equalsCashOfficeID(List<Ticket> tickets) {
    if (tickets.size() == 0) {
      return false;
    }
    int firstRowCashOfficeID = tickets.get(0).getCashOffice().getNumber();
    for (Ticket ticket : tickets) {
      if (ticket.getCashOffice() == null || ticket.getCashOffice().getNumber() != firstRowCashOfficeID) {
        return false;
      }

    }
    return true;
  }

  public void mergeCSV(List<String> inputFileNames, String outputFileName) {
    writeInCSV(readFromCSVFiles(inputFileNames), outputFileName);
  }

  public void mergeCSVToXML(List<String> inputFileNames, String fileNameDestination) {
    readFromCSVFiles(inputFileNames);
    try {
      xmlWriter.write(new ArrayList<Cinema>(mapCinemas.values()), fileNameDestination);
    } catch (IOException e) {
      logger.error("Error occured during writing to file " + fileNameDestination, e);
    }
  }

  public void mergeXML(List<String> inputFiles, String fileNameDestination) {
    mapCinemas.putAll(xmlReader.read(inputFiles));
    try {
      xmlWriter.write(new ArrayList<Cinema>(mapCinemas.values()), fileNameDestination);
    } catch (IOException e) {
      logger.error("Error occured during writing to file " + fileNameDestination, e);
    }
  }

  private Cinema findOrCreateNewCinema(String cinemaName) {
    Cinema cinema = mapCinemas.get(cinemaName);
    if (cinema == null) {
      cinema = new CinemaTranslator().fromDTO(cinemaName);
      mapCinemas.put(cinemaName, cinema);
    }
    return cinema;
  }

  private Ticket getTicketFromCSV(String[] csvParams) throws ParseException {
    if (csvParams.length != 8) {
      throw new ParseException("row contains wrong param count. Expected count=8 ", 0);
    }
    Cinema cinema = findOrCreateNewCinema(csvParams[0]);
    Movie movie = findOrCreateNewMovie(csvParams[2], cinema);
    Room room = findOrCreateNewRoom(csvParams[1], cinema);
    Seance seance = findOrCreateNewSeance(csvParams[3], cinema, room);
    seance.setMovie(movie);
    Row row = findOrCreateNewRow(csvParams[4], room);
    Place place = findOrCreateNewPlace(csvParams[5], row);
    Ticket ticket = new TicketTranslator().fromDTO(csvParams[6]);
    ticket.setPlace(place);
    ticket.setSeance(seance);
    CashOffice cashOffice = findOrCreateNewCO(csvParams[7], cinema);
    ticket.setCashOffice(cashOffice);

    return ticket;
  }

  private CashOffice findOrCreateNewCO(String coID, Cinema cinema) {
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

  private Place findOrCreateNewPlace(String placeNumber, Row row) {
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

  private Row findOrCreateNewRow(String rowNumber, Room room) {
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

  private Seance findOrCreateNewSeance(String startDateTime, Cinema cinema, Room room) throws ParseException {
    return findOrCreateNewSeance(startDateTime, cinema, room, SeanceTranslator.CSV_DATE_FORMAT);
  }

  private Room findOrCreateNewRoom(String roomName, Cinema cinema) {
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

  private Seance findOrCreateNewSeance(String startDateTime, Cinema cinema, Room room, String dateFormat) throws ParseException {
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

  private Movie findOrCreateNewMovie(String movieName, Cinema cinema) {
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

}
