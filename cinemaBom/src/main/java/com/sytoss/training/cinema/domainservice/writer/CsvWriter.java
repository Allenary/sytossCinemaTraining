package com.sytoss.training.cinema.domainservice.writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sytoss.training.cinema.bom.Ticket;
import com.sytoss.training.cinema.connector.FileSystemConnector;
import com.sytoss.training.cinema.domainservice.csvparcer.CsvParser;
import com.sytoss.training.cinema.translator.TicketTranslator;

public class CsvWriter implements IWriter {

  public void write(List<Ticket> tickets, String outputFileName) throws IOException {
    List<String> csvStrings = new ArrayList<String>();
    try {
      for (Ticket ticket : tickets) {
        csvStrings.add(new CsvParser().deParse((new TicketTranslator().toDTO(ticket))));
      }
      new FileSystemConnector().write(csvStrings, outputFileName);
    } catch (Exception e) {
      throw new IOException(e);
    }

  }

}
