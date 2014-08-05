package com.sytoss.training.cinema.domainservice;

public class SplitSplitStringStrategy implements ISplitStategy {

  public String[] splitByCommas(String rowToParse) {
    return rowToParse.split(",");
  }

}
