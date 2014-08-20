package com.sytoss.training.cinema.domainservice.reader;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.sytoss.training.cinema.domainservice.csvparcer.CsvParser;
import com.sytoss.training.cinema.domainservice.csvparcer.SplitSplitStringStrategy;
import com.sytoss.training.cinema.translator.SeanceTranslator;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class CsvReader extends AbstractReader {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public Map<String, Cinema> read(List<String> inputFileNames) {
    List<String> csvRows = new ArrayList<String>();
    for (String inputFile : inputFileNames) {
      logger.info("start with processing file: " + inputFile);
      try {
        csvRows = new FileSystemConnector().read(inputFile);
        List<String[]> parsedCsvRows;
        parsedCsvRows = parseRows(csvRows);
        if (isValidCSV(parsedCsvRows)) {
          for (String[] parsedRow : parsedCsvRows) {
            try {
              addTicketToMap(parsedRow);
            } catch (NumberFormatException e) {
              logger.warn("Error occured during row parsing", e);
            }
          }
        }
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return mapCinemas;
  }

  private List<String[]> parseRows(List<String> csvRows) throws ParseException {
    List<String[]> result = new ArrayList<String[]>();
    for (String row : csvRows) {
      result.add(new CsvParser(new SplitSplitStringStrategy()).parse(row));
    }
    return result;
  }

  private boolean isValidCSV(List<String[]> parsedCsvRows) {
    if (parsedCsvRows.get(0).length != 8) {
      return false;
    }
    String firstCoId = parsedCsvRows.get(0)[7];
    for (String[] parsedRow : parsedCsvRows) {
      if (parsedRow.length != 8) {
        return false;
      }

      if ( !parsedRow[7].equals(firstCoId)) {
        return false;
      }
    }
    return true;
  }

  private void addTicketToMap(String[] csvParams) throws ParseException {

    Cinema cinema = findOrCreateNewCinema(csvParams[0]);
    Movie movie = findOrCreateNewMovie(csvParams[2], cinema);
    Room room = findOrCreateNewRoom(csvParams[1], cinema);
    Seance seance = findOrCreateNewSeance(csvParams[3], cinema, room, SeanceTranslator.CSV_DATE_FORMAT);
    seance.setMovie(movie);
    Row row = findOrCreateNewRow(csvParams[4], room);
    Place place = findOrCreateNewPlace(csvParams[5], row);
    Ticket ticket = new TicketTranslator().fromDTO(csvParams[6]);
    ticket.setPlace(place);
    ticket.setSeance(seance);
    CashOffice cashOffice = findOrCreateNewCO(csvParams[7], cinema);
    ticket.setCashOffice(cashOffice);

  }

}
