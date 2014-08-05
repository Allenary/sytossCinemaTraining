package com.sytoss.training.cinema.domainservice;

import com.sytoss.training.cinema.domainservice.ISplitStategy;
import com.sytoss.training.cinema.domainservice.SplitIndexOfStrategy;

public class SplitIndexOfStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStategy getStrategy() {
    return new SplitIndexOfStrategy();
  }

}
