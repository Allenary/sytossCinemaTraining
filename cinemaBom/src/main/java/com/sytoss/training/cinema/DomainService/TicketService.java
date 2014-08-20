package com.sytoss.training.cinema.domainservice;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketService {

  private IWriter xmlWriter;

  private IReader xmlReader;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public TicketService(IReader reader, IWriter writer) {
    xmlReader = reader;
    xmlWriter = writer;
  }

  public void merge(List<String> inputFileNames, String fileNameDestination) {
    try {
      xmlWriter.write(xmlReader.read(inputFileNames), fileNameDestination);
    } catch (IOException e) {
      logger.error("error occurred during merge", e);
    }
  }

}
