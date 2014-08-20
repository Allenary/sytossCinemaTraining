package com.sytoss.training.cinema.domainservice.reader;

import java.util.List;

import com.sytoss.training.cinema.bom.Ticket;

public interface IReader {

  List<Ticket> read(List<String> inputFileNames);
}
