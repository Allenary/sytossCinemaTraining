package com.sytoss.training.cinema.DomainService;

public class SplitSplitStringStrategy implements ISplitStategy {

  public String[] splitByCommas(String rowToParse) {
    return rowToParse.split(",");
  }

}
