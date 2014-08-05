package com.sytoss.training.cinema.connector;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.junit.Test;

import com.sytoss.training.cinema.testutils.TestUtils;

public class CSVFileSystemConnectorTest {

  @Test
  public void shouldWriteToFile() throws IOException {
    new CSVFileSystemConnector().write(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"), "C:\\Users\\school\\testData\\simpleTest.csv");
  }

  @Test
  public void shouldReadFile() throws IOException {
    List<String> csvStrings = new CSVFileSystemConnector().read("C:\\Users\\school\\testData\\simpleTest.csv");
    for (String row : csvStrings) {
      System.out.println(row);
    }
  }

  @Test
  public void shouldsWriteInXML() throws IOException {
    Document document = new Document();
    document.setRootElement(new Element("cinemas"));

    new CSVFileSystemConnector().write(document, "C:\\Users\\school\\Desktop\\testResult.xml");
    new TestUtils().checkFiles("C:\\Users\\school\\Desktop\\all.xml", "C:\\Users\\school\\Desktop\\testResult.xml");

  }

}
