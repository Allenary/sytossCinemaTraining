package com.sytoss.training.cinema.domainservice;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TicketServiceTestRunner {

  private String getAbsolutePathFromReleative(String folder, String fileName) throws URISyntaxException {
    return new File(getClass().getResource(folder + fileName).toURI()).getAbsolutePath();
  }

  public TicketServiceTestRunner() throws URISyntaxException {
    Map<String, List<String>> inputData = new HashMap<String, List<String>>();
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

    for (Entry<String, List<String>> entry : inputData.entrySet()) {
      new TicketServiceTest3(entry);
    }
  }

}
