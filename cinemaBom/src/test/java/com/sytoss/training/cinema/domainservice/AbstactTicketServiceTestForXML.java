package com.sytoss.training.cinema.domainservice;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.sytoss.training.cinema.domainservice.IReader;
import com.sytoss.training.cinema.domainservice.IWriter;
import com.sytoss.training.cinema.domainservice.TicketService;
import com.sytoss.training.cinema.testutils.TestUtils;

public abstract class AbstactTicketServiceTestForXML {

  public abstract IReader getReader();

  public abstract IWriter getWriter();

  @Test
  public void shouldMergeXMLsToXMLDifferentCinemas() throws URISyntaxException, IOException {
    String folder = "/shouldMergeXMLsToXML";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/3tickets.xml").toURI()).getAbsolutePath());

    new TicketService(getReader(), getWriter()).mergeXML(
      inputFiles,
      new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeXMLsToXMLDifferentCOInOneCinema() throws URISyntaxException, IOException {
    String folder = "/shouldMergeXMLsToXMLDifferentCOInOneCinema";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.xml").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath());

    new TicketService(getReader(), getWriter()).mergeXML(
      inputFiles,
      new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeXMLsToXMLDifferentSeanceInOneCinema() throws URISyntaxException, IOException {
    String folder = "/shouldMergeXMLsToXMLDifferentSeanceInOneCinema";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.xml").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath());

    new TicketService(getReader(), getWriter()).mergeXML(
      inputFiles,
      new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeXMLsToXMLDSameSeanceInDiffFiles() throws URISyntaxException, IOException {
    String folder = "/shouldMergeXMLsToXMLDSameSeanceInDiffFiles";
    List<String> inputFiles = Arrays.asList(new File(getClass().getResource(folder + "/3tickets.xml").toURI()).getAbsolutePath(), new File(
      getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath());

    new TicketService(getReader(), getWriter()).mergeXML(
      inputFiles,
      new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath());

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

}
