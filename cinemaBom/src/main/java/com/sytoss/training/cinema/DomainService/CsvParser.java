package com.sytoss.training.cinema.DomainService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvParser {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

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

  private boolean isOddQuotesCount(String row) {
    return (countQuotes(row) % 2 != 0);
  }

  public String[] SplitByCommas(String rowToParse) {
    return rowToParse.split(",");
  }

  public String[] parse(String rowToParse) throws ParseException {
    String[] tempParams = SplitByCommas(rowToParse);
    String tempParam;
    List<String> resultParams = new ArrayList<String>();
    for (int i = 0; i < tempParams.length; i++ ) {
      tempParam = tempParams[i];
      if (tempParam.contains("\"")) {
        while (isOddQuotesCount(tempParam) && i < tempParams.length - 1) {
          i++ ;
          tempParam = tempParam + "," + tempParams[i];
        }
        if (isOddQuotesCount(tempParam)) {
          throw new ParseException("Odd count quotes", 0);
        }
        if (tempParam.startsWith("\"") && tempParam.endsWith("\"")) {
          tempParam = tempParam.substring(1, tempParam.length() - 1);
          logger.info(tempParam);
        } else {
          throw new ParseException("quotes is not first and/or last symbol of param", 0);
        }
      }
      resultParams.add(tempParam);
    }
    return resultParams.toArray(new String[resultParams.size()]);
  }

  public String deParse(String[] attributes) {
    String csvRow = "";
    String tempString;
    for (int i = 0; i < attributes.length - 1; i++ ) {
      tempString = attributes[i];
      if (countQuotes(tempString) > 0 || tempString.matches(".*[А-Яа-я,]+.*")) {
        tempString = "\"" + tempString + "\"";
      }
      csvRow += tempString + ",";
    }
    csvRow += attributes[attributes.length - 1];
    return csvRow;
  }

}
