package com.sytoss.training.cinema.connector;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.junit.Test;

import com.sytoss.training.cinema.testutils.TestUtils;

public class FileSystemConnectorTest {

  @Test
  public void shouldWriteToFile() throws IOException, URISyntaxException {
    new FileSystemConnector().write(
      Arrays.asList("Row1", "Row2", "Row3"),
      new File(getClass().getResource("/simpleTest2.csv").toURI()).getAbsolutePath());
    TestUtils.checkFiles(
      new File(getClass().getResource("/simpleTest.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource("/simpleTest2.csv").toURI()).getAbsolutePath());
  }

  @Test
  public void shouldReadFile() throws IOException, URISyntaxException {
    List<String> csvStrings = new FileSystemConnector().read(new File(getClass().getResource("/simpleTest.csv").toURI()).getAbsolutePath());
    assertEquals(3, csvStrings.size());
    assertEquals("Row1", csvStrings.get(0));
    assertEquals("Row2", csvStrings.get(1));
    assertEquals("Row3", csvStrings.get(2));
  }

  @Test
  public void shouldsWriteInXML() throws IOException, URISyntaxException {
    String folder = "/FileSystemConnectorTest";
    Document document = new Document();
    document.setRootElement(new Element("cinemas"));

    new FileSystemConnector().writeJDOM(document, new File(getClass().getResource(folder + "/testResult.xml").toURI()).getAbsolutePath());
    TestUtils.checkFiles(new File(getClass().getResource(folder + "/standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testResult.xml")
      .toURI()).getAbsolutePath());

  }

  @Test
  public void shouldReadXMLFileJDOM() throws JDOMException, IOException, URISyntaxException {
    String folder = "/shouldReadXMLFileJDOM";
    Document document = new FileSystemConnector().readXMLFileJDOM(new File(getClass().getResource(folder + "/simpleXML.xml").toURI())
      .getAbsolutePath());

    assertEquals("cinemas", document.getRootElement().getName());
  }
}
