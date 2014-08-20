package com.sytoss.training.cinema.domainservice.csvparcer;

public class SplitSplitStringStrategy implements ISplitStategy {

  public String[] splitByCommas(String rowToParse) {
    return rowToParse.split(",");
  }

}
