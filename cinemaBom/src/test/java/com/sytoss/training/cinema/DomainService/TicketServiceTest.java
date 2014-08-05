package com.sytoss.training.cinema.domainservice;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.sytoss.training.cinema.testutils.TestUtils;

public class TicketServiceTest {

  @Test
  public void shouldMerge2files() throws IOException, URISyntaxException {
    String folder = "/shouldMerge2files";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMerge1file() throws IOException, URISyntaxException {
    String folder = "/shouldMerge1file";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath());

    new TicketService().mergeCSV(inputFiles, new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath());

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
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

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
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

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
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

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
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

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLDifferentSeancesIn1CO() throws IOException, URISyntaxException {
    String folder = "/shouldMerge2files";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService()
      .mergeCSVToXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
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

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLTicketsInSameSeances() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLTicketsInSameSeances";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService()
      .mergeCSVToXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLDifferentCinema() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLDifferentCinema";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath());

    new TicketService()
      .mergeCSVToXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVTOXMLDifferentAllItems() throws IOException, URISyntaxException {
    String folder = "/shouldMergeCSVTOXMLDifferentAllItems";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath());

    new TicketService()
      .mergeCSVToXML(inputFiles, new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
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

    new TestUtils().checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

}
