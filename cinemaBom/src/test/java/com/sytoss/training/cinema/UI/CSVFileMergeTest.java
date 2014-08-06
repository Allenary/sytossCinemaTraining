package com.sytoss.training.cinema.ui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.sytoss.training.cinema.testutils.TestUtils;

public class CSVFileMergeTest {

  @Test(expected = IllegalArgumentException.class)
  public void ShouldRaiseExceptionWhenListIsBlank() throws IOException {
    new CSVFileMerge().main(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ShouldRaiseExceptionWhenListContainsOnly1Param() throws IOException {
    new CSVFileMerge().main(new String[] {"filename"});
  }

  @Test
  public void shouldMergeToCSV() throws URISyntaxException, IOException {
    String folder = "/shouldMerge2files";
    String[] fileNames = new String[] {
      new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.csv").toURI()).getAbsolutePath()};

    new CSVFileMerge().main(fileNames);

    TestUtils.checkFiles(new File(getClass().getResource(folder + "/Standard.csv").toURI()).getAbsolutePath(), new File(getClass()
      .getResource(folder + "/testRunResult.csv")
      .toURI()).getAbsolutePath());
  }

  @Test
  public void shouldMergeToXML() throws URISyntaxException, IOException {
    String folder = "/shouldMerge2files";
    String[] fileNames = new String[] {
      new File(getClass().getResource(folder + "/1ticket.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/3tickets.csv").toURI()).getAbsolutePath(),
      new File(getClass().getResource(folder + "/testRunResult.xml").toURI()).getAbsolutePath()};

    new CSVFileMerge().main(fileNames);

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

    new CSVFileMerge().main(fileNames);

  }
}
