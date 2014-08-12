package com.sytoss.training.cinema.domainservice;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.jdom2.JDOMException;
import org.junit.Test;

import com.sytoss.training.cinema.testutils.TestUtils;

public class TicketServiceTest {

  @Test
  public void shouldMerge2CSVToCSV() throws IOException, URISyntaxException {
    String folder = "/shouldMerge2CSVToCSV";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMerge1CSVToCSV() throws IOException, URISyntaxException {
    String folder = "/shouldMerge1CSVToCSV";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath());

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

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

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

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

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

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

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

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

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLDifferentSeancesIn1CO() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLDifferentSeancesIn1CO";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService()
      .mergeCSVToXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());

  }

  @Test
  public void shouldMergeCSVTOXMLDifferentSeancesInDifferentCO() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLDifferentSeancesInDifferentCO";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService()
      .mergeCSVToXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLTicketsInSameSeances() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLTicketsInSameSeances";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService()
      .mergeCSVToXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLDifferentCinema() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLDifferentCinema";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService()
      .mergeCSVToXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLVeryHard() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLVeryHard";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath());

    new TicketService()
      .mergeCSVToXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeXMLsToXMLDifferentCinemas() throws URISyntaxException, IOException, JDOMException, ParseException {
    String folder = "/shouldMergeXMLsToXML";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.xml").toURI()).getAbsolutePath());

    new TicketService().mergeXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeXMLsToXMLDifferentCOInOneCinema() throws URISyntaxException, IOException, JDOMException, ParseException {
    String folder = "/shouldMergeXMLsToXMLDifferentCOInOneCinema";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.xml").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath());

    new TicketService().mergeXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeXMLsToXMLDifferentSeanceInOneCinema() throws URISyntaxException, IOException, JDOMException, ParseException {
    String folder = "/shouldMergeXMLsToXMLDifferentSeanceInOneCinema";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.xml").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath());

    new TicketService().mergeXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeXMLsToXMLDSameSeanceInDiffFiles() throws URISyntaxException, IOException, JDOMException, ParseException {
    String folder = "/shouldMergeXMLsToXMLDSameSeanceInDiffFiles";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.xml").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath());

    new TicketService().mergeXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }
}
