package com.sytoss.training.cinema.DomainService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.CSVFileSystemConnector;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class TicketService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private List<Ticket> read(List<String> fileNames) {

    List<String> csvRows = new ArrayList<String>();
    List<Ticket> allTickets = new ArrayList<Ticket>();
    List<Ticket> ticketsIn1File;
    boolean isFileSkipped;
    for (String inputFile : fileNames) {
      logger.warn("start with processing file: " + inputFile);
      csvRows = new CSVFileSystemConnector().read(inputFile);
      ticketsIn1File = new ArrayList<Ticket>();
      isFileSkipped = false;
      for (String row : csvRows) {
        try {
          ticketsIn1File.add(new TicketTranslator().fromDTO(new CsvParser().parse(row)));
        } catch (ParseException e) {
          logger.warn("file " + inputFile + " was skipped. Reason: Corrupted data.");
          isFileSkipped = true;
        }
      }
      if ( !isFileSkipped && equalsCashOfficeID(ticketsIn1File)) {
        allTickets.addAll(ticketsIn1File);
        logger.debug("file added to merge: " + inputFile);
      }

    }

    return allTickets;

  }

  public void write(List<Ticket> tickets, String fileNameDestination) {
    List<String> csvStrings = new ArrayList<String>();
    for (Ticket ticket : tickets) {
      csvStrings.add(new CsvParser().deParse((new TicketTranslator().toDTO(ticket))));
    }
    new CSVFileSystemConnector().write(csvStrings, fileNameDestination);
  }

  public boolean equalsCashOfficeID(List<Ticket> tickets) {
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
    List<Ticket> inputTickets = read(inputFileNames);
    List<String> rowsToOutput = new ArrayList<String>();
    for (Ticket ticket : inputTickets) {
      rowsToOutput.add(new CsvParser().deParse(new TicketTranslator().toDTO(ticket)));
    }
    CSVFileSystemConnector writer = new CSVFileSystemConnector();
    writer.write(rowsToOutput, outputFileName);
  }
}
