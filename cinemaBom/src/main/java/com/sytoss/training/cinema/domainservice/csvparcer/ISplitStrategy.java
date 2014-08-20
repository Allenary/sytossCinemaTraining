package com.sytoss.training.cinema.domainservice.csvparcer;

public interface ISplitStrategy {

  String[] splitByCommas(String rowToParse);
}
