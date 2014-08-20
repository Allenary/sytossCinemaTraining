package com.sytoss.training.cinema.domainservice.writer;

import java.io.IOException;
import java.util.Map;

import com.sytoss.training.cinema.bom.Cinema;

public interface IWriter {

  void write(Map<String, Cinema> cinemas, String outputFileName) throws IOException;
}
