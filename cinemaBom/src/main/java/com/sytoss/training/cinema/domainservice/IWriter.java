package com.sytoss.training.cinema.domainservice;

import java.io.IOException;
import java.util.List;

import com.sytoss.training.cinema.bom.Cinema;

public interface IWriter {

  void write(List<Cinema> cinemas, String outputFileName) throws IOException;
}
