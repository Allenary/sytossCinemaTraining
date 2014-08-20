package com.sytoss.training.cinema.domainservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.sytoss.training.cinema.bom.CashOffice;
import com.sytoss.training.cinema.bom.Cinema;
import com.sytoss.training.cinema.bom.Movie;
import com.sytoss.training.cinema.bom.Room;
import com.sytoss.training.cinema.bom.Row;
import com.sytoss.training.cinema.bom.Seance;
import com.sytoss.training.cinema.domainservice.reader.CsvReader;
import com.sytoss.training.cinema.domainservice.writer.CsvWriter;
import com.sytoss.training.cinema.domainservice.writer.JdomXmlWriter;
import com.sytoss.training.cinema.testutils.TestUtils;

public class TicketServiceTest {

  @Test
  public void shouldMerge2CSVToCSV() throws IOException, URISyntaxException {
    String folder = "/shouldMerge2CSVToCSV";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new CsvWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMerge1CSVToCSV() throws IOException, URISyntaxException {
    String folder = "/shouldMerge1CSVToCSV";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new CsvWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldNotMergeFileWithDifferentCashOffice() throws IOException, URISyntaxException {
    String folder = "/shouldNotMergeFileWithDifferentCashOffice";
    List<String> inputFiles = Arrays.asList(
      new File(getClass().getResource(folder + "/validTicket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/emptyCashOffice.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/anotherCashOffice.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new CsvWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldNotMergeFileWithRowsWithWrongLength() throws IOException, URISyntaxException {
    String folder = "/shouldNotMergeFileWithRowsWithWrongLength";
    List<String> inputFiles = Arrays.asList(
      new File(getClass().getResource(folder + "/validTicket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/lessParamsInRow.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/moreParamsInRow.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new CsvWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldNotMergeFileWithRowsWithWrongParams() throws IOException, URISyntaxException {
    String folder = "/shouldNotMergeFileWithRowsWithWrongParams";
    List<String> inputFiles = Arrays.asList(
      new File(getClass().getResource(folder + "/validTicket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/wrongDate.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/wrongPrice.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new CsvWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeFileWithQuotesInParams() throws IOException, URISyntaxException {
    String folder = "/shouldMergeFileWithQuotesInParams";
    List<String> inputFiles = Arrays.asList(
      new File(getClass().getResource(folder + "/CommasInQuotes.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/QuotesInQuotes.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/invalidTicket.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new CsvWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLDifferentSeancesIn1CO() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLDifferentSeancesIn1CO";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new JdomXmlWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());

  }

  @Test
  public void shouldMergeCSVTOXMLDifferentSeancesInDifferentCO() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLDifferentSeancesInDifferentCO";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new JdomXmlWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLTicketsInSameSeances() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLTicketsInSameSeances";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new JdomXmlWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLDifferentCinema() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLDifferentCinema";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new JdomXmlWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLVeryHard() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLVeryHard";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new JdomXmlWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldCreateSingleBOMObjectForTicketsWithSameData() throws URISyntaxException, IOException {
    String folder = "/shouldCreateSingleBOMObjectForTicketsWithSameData";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    Map<String, Cinema> cinemas = new CsvReader().read(inputFiles);

    assertEquals(1, cinemas.values().size());

    Cinema cinema = cinemas.values().iterator().next();
    Iterator<CashOffice> coIterator = cinema.cashOfficeIterator();
    assertTrue(coIterator.hasNext());
    coIterator.next();
    assertFalse(coIterator.hasNext());

    Iterator<Room> roomIterator = cinema.roomIterator();
    assertTrue(roomIterator.hasNext());
    Room room = roomIterator.next();
    assertFalse(roomIterator.hasNext());

    Iterator<Row> rowIterator = room.getAllRows();
    assertTrue(rowIterator.hasNext());
    rowIterator.next();
    assertTrue(rowIterator.hasNext());
    rowIterator.next();
    assertFalse(rowIterator.hasNext());

    Iterator<Movie> movieIterator = cinema.movieIterator();
    assertTrue(movieIterator.hasNext());
    movieIterator.next();
    assertFalse(movieIterator.hasNext());

    Iterator<Seance> seanceIterator = cinema.seanceIterator();
    assertTrue(seanceIterator.hasNext());
    seanceIterator.next();
    assertTrue(seanceIterator.hasNext());
    seanceIterator.next();
    assertFalse(seanceIterator.hasNext());

  }

  @Test
  public void shouldNotMergeCSVWithDifferentCOToXML() throws URISyntaxException, IOException {
    String folder = "/shouldNotMergeFileWithDifferentCashOffice";
    List<String> inputFiles = Arrays.asList(
      new File(getClass().getResource(folder + "/validTicket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/emptyCashOffice.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/anotherCashOffice.csv").toURI()).getAbsolutePath());

    new TicketService(new CsvReader(), new JdomXmlWriter()).merge(inputFiles, new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

}
