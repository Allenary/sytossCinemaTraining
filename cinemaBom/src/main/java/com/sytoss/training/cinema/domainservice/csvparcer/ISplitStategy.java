package com.sytoss.training.cinema.domainservice.csvparcer;

public interface ISplitStategy {

  String[] splitByCommas(String rowToParse);
}
