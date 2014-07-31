package com.sytoss.training.cinema.connector;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CSVFileSystemConnectorTest {

  @Test
  public void shouldWriteToFile() throws IOException {
    new CSVFileSystemConnector().write(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"), "C:\\Users\\school\\testData\\simpleTest.csv");
  }

  @Test
  public void shouldReadFile() {
    List<String> csvStrings = new CSVFileSystemConnector().read("C:\\Users\\school\\testData\\simpleTest.csv");
    for (String row : csvStrings) {
      System.out.println(row);
    }
  }

}
