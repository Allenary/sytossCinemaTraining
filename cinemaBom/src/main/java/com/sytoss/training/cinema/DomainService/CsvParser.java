package com.sytoss.training.cinema.DomainService;

import java.util.ArrayList;
import java.util.List;

public class CsvParser {

  private int countQuotes(String row, int startPosition, int endPosition) {
    int count = 0;
    int index = row.indexOf("\"", startPosition);
    while (index != -1 && index <= endPosition) {
      count++ ;
      index = row.indexOf("\"", index + 1);
    }
    return count;
  }

  public String[] parse(String rowToParse) {
    List<String> tokens = new ArrayList<String>();
    String delimeter = ",";
    int beginIndex = 0;
    int endIndex = rowToParse.indexOf(delimeter, beginIndex);
    int countQuotes;
    while (endIndex != -1) {
      countQuotes = countQuotes(rowToParse, beginIndex, endIndex);
      while (countQuotes % 2 != 0) {
        endIndex = rowToParse.indexOf(delimeter, endIndex + 1);
        countQuotes = countQuotes(rowToParse, beginIndex, endIndex);
        System.out.print("end index changed to " + endIndex + "\n");
      }
      tokens.add(rowToParse.substring(beginIndex, endIndex));
      System.out.print(rowToParse.substring(beginIndex, endIndex) + "\n");
      beginIndex = endIndex + 1;
      endIndex = rowToParse.indexOf(delimeter, beginIndex);
    }
    tokens.add(rowToParse.substring(beginIndex, rowToParse.length()));
    return tokens.toArray(new String[8]);

  }
}
