package com.sytoss.training.cinema.domainservice;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

  private String quote = "\"";

  private String separator = ",";

  private ISplitStategy strategy;

  public CsvParser(ISplitStategy splitStategy) {
    strategy = splitStategy;
  }

  private int countQuotes(String row, int startPosition, int endPosition) {
    int count = 0;
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

  private String deleteExternalQuotes(String param) throws ParseException {
    if (param.startsWith(quote) && param.endsWith(quote)) {
      param = param.substring(1, param.length() - 1);
    } else {
      throw new ParseException("quotes is not first and/or last symbol of param", 0);
    }
    return param;
  }

  public String[] parse(String rowToParse) throws ParseException {
    String[] tempParams = strategy.splitByCommas(rowToParse);
    String tempParam;
    List<String> resultParams = new ArrayList<String>();
    for (int i = 0; i < tempParams.length; i++ ) {
      tempParam = tempParams[i];
      if (tempParam.contains(quote)) {
        while (isOddQuotesCount(tempParam) && i < tempParams.length - 1) {
          i++ ;
          tempParam = tempParam + separator + tempParams[i];
        }
        if (isOddQuotesCount(tempParam)) {
          throw new ParseException("Odd count quotes", 0);
        }
        tempParam = deleteExternalQuotes(tempParam);
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
      if (countQuotes(tempString) > 0 || tempString.matches(".*[,А-Яа-я\"]+.*")) {
        tempString = quote + tempString + quote;
      }
      csvRow += tempString + separator;
    }
    csvRow += attributes[attributes.length - 1];
    return csvRow;
  }

}
