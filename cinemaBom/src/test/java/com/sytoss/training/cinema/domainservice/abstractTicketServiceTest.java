package com.sytoss.training.cinema.domainservice;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import com.sytoss.training.cinema.testutils.TestUtils;

public abstract class abstractTicketServiceTest {

  public abstract Entry<String, List<String>> getTestData();

  private String getAbsolutePathFromReleative(String folder, String fileName) throws URISyntaxException {
    return new File(getClass().getResource(folder + fileName).toURI()).getAbsolutePath();
  }

  @Test
  public void shouldRunAllCSVToXMLTest() throws IOException, URISyntaxException {
    Entry<String, List<String>> testdata = getTestData();

    new TicketService().mergeCSVToXML(testdata.getValue(), getAbsolutePathFromReleative(testdata.getKey(), "/testRunResult.xml"));
    TestUtils.checkFiles(
      getAbsolutePathFromReleative(testdata.getKey(), "/Standard.xml"),
      getAbsolutePathFromReleative(testdata.getKey(), "/testRunResult.xml"));
  }
}
