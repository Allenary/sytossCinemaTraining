package com.sytoss.training.cinema.DomainService;

public class SplitIndexOfStrategyTest extends AbstractCsvParserTest {

  @Override
  public ISplitStategy getStrategy() {
    return new SplitIndexOfStrategy();
  }

}
