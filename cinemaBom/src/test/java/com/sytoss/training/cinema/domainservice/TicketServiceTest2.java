package com.sytoss.training.cinema.domainservice;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.sytoss.training.cinema.testutils.TestUtils;

public class TicketServiceTest2 {

  Map<String, List<String>> inputData;

  private String getAbsolutePathFromReleative(String folder, String fileName) throws URISyntaxException {

    return new File(getClass().getResource(folder + fileName).toURI()).getAbsolutePath();
  }

  @Test
  public void shouldRunAllCSVToXMLTest() throws IOException, URISyntaxException {
    inputData = new HashMap<String, List<String>>();
    inputData.put(
      "/shouldMerge2files",
      Arrays.asList(new String[] {
        getAbsolutePathFromReleative("/shouldMerge2files", "/1ticket.csv"),
        getAbsolutePathFromReleative("/shouldMerge2files", "/3tickets.csv")}));
    inputData.put(
      "/shouldMergeCSVTOXMLDifferentSeancesInDifferentCO",
      Arrays.asList(new String[] {
        getAbsolutePathFromReleative("/shouldMergeCSVTOXMLDifferentSeancesInDifferentCO", "/1ticket.csv"),
        getAbsolutePathFromReleative("/shouldMergeCSVTOXMLDifferentSeancesInDifferentCO", "/3tickets.csv")}));

    Set<String> keys = inputData.keySet();
    for (String key : keys) {
      new TicketService().mergeCSVToXML(inputData.get(key), getAbsolutePathFromReleative(key, "/testRunResult.xml"));
      TestUtils.checkFiles(getAbsolutePathFromReleative(key, "/Standard.xml"), getAbsolutePathFromReleative(key, "/testRunResult.xml"));
      System.out.println("Test " + key + " passed!");
    }
  }

}
