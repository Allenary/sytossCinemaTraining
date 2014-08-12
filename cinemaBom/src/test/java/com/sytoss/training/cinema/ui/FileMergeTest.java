package com.sytoss.training.cinema.ui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.sytoss.training.cinema.testutils.TestUtils;

public class FileMergeTest {

  @Test(expected = IllegalArgumentException.class)
  public void ShouldRaiseExceptionWhenListIsBlank() {
    new FileMerge().main(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ShouldRaiseExceptionWhenListContainsOnly1Param() {
    new FileMerge().main(new String[] {"filename"});
  }

  @Test
  public void shouldMergeCSVToCSV() throws URISyntaxException, IOException {
    String folder = "/shouldMerge2files";
    String[] fileNames = new String[] {
      new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath()};

    new FileMerge().main(fileNames);

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeCSVToXML() throws URISyntaxException, IOException {
    String folder = "/shouldMerge2files";
    String[] fileNames = new String[] {
      new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath()};

    new FileMerge().main(fileNames);

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldShowMessageIfInvalidOutputFileName() throws URISyntaxException, IOException {
    String folder = "/shouldMerge2files";
    String[] fileNames = new String[] {
      new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath(),
      "notCSVORXML.txt"};

    new FileMerge().main(fileNames);

  }

  @Test
  public void shouldMergeXMLToXML() throws URISyntaxException, IOException {
    String folder = "/shouldMergeXMLsToXML";
    String[] fileNames = new String[] {
      new File(getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/3tickets.xml").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath()};

    new FileMerge().main(fileNames);

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.xml").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.xml")
      .toURI()).getAbsolutePath());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaisErroWithXMLToCSV() throws URISyntaxException, IOException {
    String folder = "/shouldRaisErroWithXMLToCSV";
    String[] fileNames = new String[] {
      new File(getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/3tickets.xml").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath()};

    new FileMerge().main(fileNames);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRaisErroWithXMLAndCSV() throws URISyntaxException, IOException {
    String folder = "/shouldRaisErroWithXMLAndCSV";
    String[] fileNames = new String[] {
      new File(getClass().getResource(folder + "/1ticket.xml").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath()};

    new FileMerge().main(fileNames);
  }
}
