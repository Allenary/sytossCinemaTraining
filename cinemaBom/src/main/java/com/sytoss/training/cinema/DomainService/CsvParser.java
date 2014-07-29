package com.sytoss.training.cinema.DomainService;

import java.util.ArrayList;
import java.util.List;

import bom.exception.CsvStringParseException;

public class CsvParser {

  private int countQuotes(String row, int startPosition, int endPosition) {
    int count = 0;
    String quote = "\"";
    int index = row.indexOf(quote, startPosition);
    while (index != -1 && index <= endPosition) {
      count++ ;
      index = row.indexOf(quote, index + 1);
    }
    return count;
  }

  private int countQuotes(String row) {
    return countQuotes(row, 0, row.length() - 1);
  }

  public String[] parse(String rowToParse) {
    List<String> tokens = new ArrayList<String>();
    String delimeter = ",";
    int beginIndex = 0;
    int endIndex = rowToParse.indexOf(delimeter, beginIndex);
    while (endIndex != -1) {
      while (countQuotes(rowToParse, beginIndex, endIndex) % 2 != 0) {
        endIndex = rowToParse.indexOf(delimeter, endIndex + 1);
        if (endIndex == -1) {
          throw new CsvStringParseException("String is incorrect: odd count of quotes found.");
        }
      }
      tokens.add(rowToParse.substring(beginIndex, endIndex));
      beginIndex = endIndex + 1;
      endIndex = rowToParse.indexOf(delimeter, beginIndex);
    }
    tokens.add(rowToParse.substring(beginIndex, rowToParse.length()));
    return tokens.toArray(new String[8]);

  }

  public String deParse(String[] attributes) {
    String csvRow = "";
    String tempString;
    for (int i = 0; i < attributes.length - 1; i++ ) {
      tempString = attributes[i];
      //      tempString.matches("")
      if (countQuotes(tempString) > 0 || tempString.matches(".*[А-Яа-я,]+.*")) {
        tempString = "\"" + tempString + "\"";
      }
      csvRow += tempString + ",";
    }
    csvRow += attributes[attributes.length - 1];
    return csvRow;
  }

}
