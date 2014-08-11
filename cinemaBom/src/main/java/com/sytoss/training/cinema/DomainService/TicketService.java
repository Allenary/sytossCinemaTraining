package com.sytoss.training.cinema.domainservice;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.CashOfficeTest;
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

  private Map<String, Cinema> mapCinemas;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public TicketService() {
    mapCinemas = new HashMap<String, Cinema>();
  }

  private List<Ticket> readFromFile(List<String> fileNames) {

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
            //ticketsIn1File.add(new TicketTranslator().fromDTO(new CsvParser(new SplitSplitStringStrategy()).parse(row)));
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

  private void writeInFile(List<Ticket> tickets, String fileNameDestination) {
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
    writeInFile(readFromFile(inputFileNames), outputFileName);
  }

  private CashOffice getSameCashOfficeInCinema(Cinema cinema, CashOffice searchedCashOffice) {
    Iterator<CashOffice> cashOffices = cinema.cashOfficeIterator();
    CashOffice tempCashOffice;
    while (cashOffices.hasNext()) {
      tempCashOffice = cashOffices.next();
      if (tempCashOffice.equals(searchedCashOffice)) {
        return tempCashOffice;
      }
    }
    return null;
  }

  private List<Cinema> getCinemasFromTickets(List<Ticket> tickets) {
    List<Cinema> cinemas = new ArrayList<Cinema>();
    Cinema tempCinema;
    int tempIndexCinema;
    CashOffice tempCashOffice;
    for (Ticket ticket : tickets) {
      tempCinema = ticket.getCashOffice().getCinema();
      tempIndexCinema = cinemas.indexOf(tempCinema);
      if (tempIndexCinema == -1) {
        cinemas.add(tempCinema);
      } else {
        tempCashOffice = getSameCashOfficeInCinema(cinemas.get(tempIndexCinema), ticket.getCashOffice());
        if (tempCashOffice == null) {
          cinemas.get(tempIndexCinema).addCashOffice(ticket.getCashOffice());
        } else {
          if ( !tempCashOffice.exists(ticket)) {
            tempCashOffice.addTicket(ticket);
          }
        }
      }

    }
    return cinemas;
  }

  private void writeInXML(List<Cinema> cinemas, String fileNameDestination) throws IOException {
    List<Element> cinemaElements = new ArrayList<Element>();
    for (Cinema cinema : cinemas) {
      cinemaElements.add(new CinemaTranslator().toElement(cinema));
    }
    Element rootElement = new Element("cinemas");
    rootElement.addContent(cinemaElements);
    Document document = new Document();
    document.setRootElement(rootElement);
    new FileSystemConnector().write(document, fileNameDestination);
  }

  public void mergeCSVToXML(List<String> inputFileNames, String fileNameDestination) throws IOException {
    List<Ticket> tickets = readFromFile(inputFileNames);
    //    List<Cinema> cinemas = getCinemasFromTickets(tickets);

    writeInXML(new ArrayList<Cinema>(mapCinemas.values()), fileNameDestination);
  }

  public void mergeXML(List<String> inputFiles, String absolutePath) throws JDOMException, IOException, ParseException {
    List<Cinema> cinemas = new ArrayList<Cinema>();
    FileSystemConnector fsc = new FileSystemConnector();
    for (String inputFile : inputFiles) {
      Document doc = fsc.readXMLFileJDOM(inputFile);
      List<Element> cinemaElements = doc.getRootElement().getChildren("cinema");
      for (Element cinemaElement : cinemaElements) {
        cinemas.add(new CinemaTranslator().fromDTO(cinemaElement));
      }
    }
    List<Ticket> tickets = new ArrayList<Ticket>();
    for (Cinema cinema : cinemas) {
      Iterator<CashOffice> cashOffices = cinema.cashOfficeIterator();
      while (cashOffices.hasNext()) {
        Iterator<Ticket> ticketsIterator = cashOffices.next().tiketsIterator();
        while (ticketsIterator.hasNext()) {
          tickets.add(ticketsIterator.next());
        }
      }
    }

    writeInXML(getCinemasFromTickets(tickets), absolutePath);
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
    Seance seance;
    Seance seanceT = new SeanceTranslator().fromDTO(startDateTime);
    Iterator<Seance> seances = cinema.seanceIterator();
    while (seances.hasNext()) {
      seance = seances.next();
      if (room == seance.getRoom() && seanceT.getStartDateTime() == seance.getStartDateTime()) {
        return seance;
      }
    }
    seanceT.setRoom(room);
    cinema.addSeance(seanceT);
    return seanceT;
  }

  private Room findOrCreateNewRoom(String roomName, Cinema cinema) {
    Iterator<Room> rooms = cinema.roomIterator();
    Room room;
    logger.info("searched room: " + roomName);
    while (rooms.hasNext()) {
      room = rooms.next();
      logger.info("current room: " + room.getName());
      if (room.getName().equals(roomName)) {
        logger.info("Finded room " + roomName);
        return room;
      }
    }
    room = new RoomTranslator().fromDTO(roomName);
    cinema.addRoom(room);
    logger.info("added room" + room.getName());
    return room;
  }

  private Movie findOrCreateNewMovie(String movieName, Cinema cinema) {
    Iterator<Movie> movies = cinema.movieIterator();
    Movie movie;
    while (movies.hasNext()) {
      movie = movies.next();
      if (movie.getName() == movieName) {
        return movie;
      }
    }
    movie = new MovieTranslator().fromDTO(movieName);
    cinema.addMovie(movie);
    return movie;
  }
}
