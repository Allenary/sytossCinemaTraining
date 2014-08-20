package com.sytoss.training.cinema.domainservice.csvparcer;

import java.util.ArrayList;
import java.util.List;

public class SplitIndexOfStrategy implements ISplitStategy {

  public String[] splitByCommas(String rowToParse) {
    List<String> output = new ArrayList<String>();
    int startIndex = 0;
    int endIndex = rowToParse.indexOf(",");
    while (endIndex != -1) {
      output.add(rowToParse.substring(startIndex, endIndex));
      startIndex = endIndex + 1;
      endIndex = rowToParse.indexOf(",", startIndex);
    }
    output.add(rowToParse.substring(startIndex, rowToParse.length()));
    return output.toArray(new String[output.size()]);
  }

}
