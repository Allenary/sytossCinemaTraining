package com.sytoss.training.cinema.DomainService;

import java.util.ArrayList;
import java.util.List;

public class CsvParser {

  public String[] parse(String rowToParse) {
    List<String> tokens = new ArrayList<String>();
    String delimeter = ",";
    int beginIndex = 0;
    int endIndex = rowToParse.indexOf(delimeter, beginIndex);
    System.out.print("begin:" + beginIndex + " end:" + endIndex + "\n");
    while (endIndex != -1) {
      tokens.add(rowToParse.substring(beginIndex, endIndex));
      beginIndex = endIndex + 1;
      endIndex = rowToParse.indexOf(delimeter, beginIndex);
    }
    tokens.add(rowToParse.substring(beginIndex, rowToParse.length()));
    return tokens.toArray(new String[8]);

  }
}
