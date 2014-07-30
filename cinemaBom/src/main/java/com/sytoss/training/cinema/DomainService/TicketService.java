package com.sytoss.training.cinema.DomainService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.CSVFileSystemConnector;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class TicketService {

  private List<Ticket> read(List<String> fileNames) {
    List<String> csvRows = new ArrayList<String>();
    for (String inputFile : fileNames) {
      csvRows.addAll(new CSVFileSystemConnector().read(inputFile));
    }
    List<Ticket> tickets = new ArrayList<Ticket>();
    for (String row : csvRows) {
      tickets.add(new TicketTranslator().fromDTO(new CsvParser().parse(row)));
    }

    return tickets;

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
    if (equalsCashOfficeID(inputTickets)) {
      List<String> rowsToOutput = new ArrayList<String>();
      for (Ticket ticket : inputTickets) {
        rowsToOutput.add(new CsvParser().deParse(new TicketTranslator().toDTO(ticket)));
      }
      CSVFileSystemConnector writer = new CSVFileSystemConnector(true);
      File outputFile = new File(outputFileName);
      if (outputFile.exists()) {
        outputFile.delete();
      }
      writer.write(rowsToOutput, outputFileName);
    }
  }
}
