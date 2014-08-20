package com.sytoss.training.cinema.domainservice.readertest;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import com.sytoss.training.cinema.domainservice.reader.CsvReader;
import com.sytoss.training.cinema.domainservice.reader.IReader;

public class CsvReaderTest extends AbstractReaderTest {

  @Override
  public IReader getReader() {
    return new CsvReader();
  }

  @Override
  public List<String> getInputFiles() throws URISyntaxException {
    return Arrays.asList(new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());
  }

}
