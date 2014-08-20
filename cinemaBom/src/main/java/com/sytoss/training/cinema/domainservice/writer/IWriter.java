package com.sytoss.training.cinema.domainservice.writer;

import java.io.IOException;
import java.util.List;

import com.sytoss.training.cinema.bom.Ticket;

public interface IWriter {

  void write(List<Ticket> tickets, String outputFileName) throws IOException;
}
