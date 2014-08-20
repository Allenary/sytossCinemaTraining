package com.sytoss.training.cinema.domainservice.csvparcer;

public class SplitSplitStringStrategy implements ISplitStrategy {

  public String[] splitByCommas(String rowToParse) {
    return rowToParse.split(",");
  }

}
