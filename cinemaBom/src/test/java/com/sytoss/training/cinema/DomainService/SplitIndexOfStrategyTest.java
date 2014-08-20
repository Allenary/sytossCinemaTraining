package com.sytoss.training.cinema.domainservice;

import com.sytoss.training.cinema.domainservice.csvparcer.ISplitStategy;
import com.sytoss.training.cinema.domainservice.csvparcer.SplitIndexOfStrategy;

public class SplitIndexOfStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStategy getStrategy() {
    return new SplitIndexOfStrategy();
  }

}
